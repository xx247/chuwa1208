package com.shopping.itemservice.service;

import com.shopping.itemservice.dto.InventoryResponse;
import com.shopping.itemservice.dto.ReserveResponse;

public interface InventoryService {
    InventoryResponse getInventory(String itemId);
    InventoryResponse setInventory(String itemId, long availableUnits);
    ReserveResponse reserve(String itemId, long quantity);
    InventoryResponse release(String itemId, long quantity);
}