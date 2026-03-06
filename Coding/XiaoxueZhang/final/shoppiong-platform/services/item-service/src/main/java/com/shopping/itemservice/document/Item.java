package com.shopping.itemservice.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Document("items")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Item {

    // We use itemId as Mongo _id
    @Id
    private String itemId;

    private String name;

    private BigDecimal unitPrice;

    private String upc;

    private List<String> pictureUrls;

    private long availableUnits;

    // flexible metadata
    private Map<String, Object> attributes;

    private Instant createdAt;
    private Instant updatedAt;
}