package com.shopping.itemservice.service;

import com.shopping.itemservice.dto.CreateItemRequest;
import com.shopping.itemservice.dto.ItemResponse;

import java.util.List;

public interface ItemService {
    ItemResponse create(CreateItemRequest request);
    ItemResponse get(String itemId);
    List<ItemResponse> list();
}