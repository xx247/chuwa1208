package com.shopping.itemservice.service;

import com.shopping.itemservice.document.Item;
import com.shopping.itemservice.dto.CreateItemRequest;
import com.shopping.itemservice.dto.ItemResponse;
import com.shopping.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public ItemResponse create(CreateItemRequest request) {
        String itemId = request.getItemId().trim();

        if (itemRepository.existsById(itemId)) {
            throw new IllegalArgumentException("Item already exists: " + itemId);
        }
        if (itemRepository.existsByUpc(request.getUpc().trim())) {
            throw new IllegalArgumentException("UPC already exists: " + request.getUpc());
        }

        Instant now = Instant.now();
        Item item = Item.builder()
                .itemId(itemId)
                .name(request.getName().trim())
                .unitPrice(request.getUnitPrice())
                .upc(request.getUpc().trim())
                .pictureUrls(request.getPictureUrls())
                .availableUnits(request.getAvailableUnits())
                .attributes(request.getAttributes())
                .createdAt(now)
                .updatedAt(now)
                .build();

        return toResponse(itemRepository.save(item));
    }

    @Override
    public ItemResponse get(String itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemId));
        return toResponse(item);
    }

    @Override
    public List<ItemResponse> list() {
        return itemRepository.findAll().stream().map(this::toResponse).toList();
    }

    private ItemResponse toResponse(Item item) {
        return ItemResponse.builder()
                .itemId(item.getItemId())
                .name(item.getName())
                .unitPrice(item.getUnitPrice())
                .upc(item.getUpc())
                .pictureUrls(item.getPictureUrls())
                .availableUnits(item.getAvailableUnits())
                .attributes(item.getAttributes())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .build();
    }
}