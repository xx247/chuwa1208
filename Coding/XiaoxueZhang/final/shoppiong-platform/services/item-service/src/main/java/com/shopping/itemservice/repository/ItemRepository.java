package com.shopping.itemservice.repository;

import com.shopping.itemservice.document.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
    boolean existsByUpc(String upc);
}