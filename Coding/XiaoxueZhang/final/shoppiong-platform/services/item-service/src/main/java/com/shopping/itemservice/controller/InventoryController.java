package com.shopping.itemservice.controller;

import com.shopping.itemservice.dto.*;
import com.shopping.itemservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{itemId}")
    public InventoryResponse get(@PathVariable String itemId) {
        return inventoryService.getInventory(itemId);
    }

    @PostMapping("/update")
    public InventoryResponse update(@Valid @RequestBody UpdateInventoryRequest req) {
        return inventoryService.setInventory(req.getItemId(), req.getAvailableUnits());
    }

    @PostMapping("/reserve")
    public ReserveResponse reserve(@Valid @RequestBody ReserveRequest req) {
        return inventoryService.reserve(req.getItemId(), req.getQuantity());
    }

    @PostMapping("/release")
    public InventoryResponse release(@Valid @RequestBody ReserveRequest req) {
        return inventoryService.release(req.getItemId(), req.getQuantity());
    }
}