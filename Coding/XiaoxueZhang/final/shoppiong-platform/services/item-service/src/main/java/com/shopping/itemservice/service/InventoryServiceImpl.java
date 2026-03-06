package com.shopping.itemservice.service;

import com.shopping.itemservice.document.Item;
import com.shopping.itemservice.dto.InventoryResponse;
import com.shopping.itemservice.dto.ReserveResponse;
import com.shopping.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final ItemRepository itemRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public InventoryResponse getInventory(String itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemId));
        return InventoryResponse.builder()
                .itemId(item.getItemId())
                .availableUnits(item.getAvailableUnits())
                .build();
    }

    @Override
    public InventoryResponse setInventory(String itemId, long availableUnits) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemId));

        item.setAvailableUnits(availableUnits);
        item.setUpdatedAt(Instant.now());
        Item saved = itemRepository.save(item);

        return InventoryResponse.builder()
                .itemId(saved.getItemId())
                .availableUnits(saved.getAvailableUnits())
                .build();
    }

    @Override
    public ReserveResponse reserve(String itemId, long quantity) {
        // Atomic: only decrement if availableUnits >= quantity
        Query query = new Query(Criteria.where("_id").is(itemId)
                .and("availableUnits").gte(quantity));

        Update update = new Update()
                .inc("availableUnits", -quantity)
                .set("updatedAt", Instant.now());

        FindAndModifyOptions opts = FindAndModifyOptions.options().returnNew(true);

        Item updated = mongoTemplate.findAndModify(query, update, opts, Item.class);

        if (updated == null) {
            // Either item not found or insufficient stock
            boolean exists = itemRepository.existsById(itemId);
            return ReserveResponse.builder()
                    .itemId(itemId)
                    .reservedQuantity(0)
                    .remainingUnits(exists ? itemRepository.findById(itemId).get().getAvailableUnits() : 0)
                    .success(false)
                    .message(exists ? "Insufficient inventory" : "Item not found")
                    .build();
        }

        return ReserveResponse.builder()
                .itemId(updated.getItemId())
                .reservedQuantity(quantity)
                .remainingUnits(updated.getAvailableUnits())
                .success(true)
                .message("Reserved")
                .build();
    }

    @Override
    public InventoryResponse release(String itemId, long quantity) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemId));

        item.setAvailableUnits(item.getAvailableUnits() + quantity);
        item.setUpdatedAt(Instant.now());
        Item saved = itemRepository.save(item);

        return InventoryResponse.builder()
                .itemId(saved.getItemId())
                .availableUnits(saved.getAvailableUnits())
                .build();
    }
}