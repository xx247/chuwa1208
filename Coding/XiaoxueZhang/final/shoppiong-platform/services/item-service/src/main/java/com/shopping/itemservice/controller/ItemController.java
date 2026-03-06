package com.shopping.itemservice.controller;

import com.shopping.itemservice.dto.CreateItemRequest;
import com.shopping.itemservice.dto.ItemResponse;
import com.shopping.itemservice.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse create(@Valid @RequestBody CreateItemRequest request) {
        return itemService.create(request);
    }

    @GetMapping("/{itemId}")
    public ItemResponse get(@PathVariable String itemId) {
        return itemService.get(itemId);
    }

    @GetMapping
    public List<ItemResponse> list() {
        return itemService.list();
    }
}