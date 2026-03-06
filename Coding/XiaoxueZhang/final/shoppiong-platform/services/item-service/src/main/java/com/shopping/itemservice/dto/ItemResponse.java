package com.shopping.itemservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ItemResponse {
    private String itemId;
    private String name;
    private BigDecimal unitPrice;
    private String upc;
    private List<String> pictureUrls;
    private long availableUnits;
    private Map<String, Object> attributes;
    private Instant createdAt;
    private Instant updatedAt;
}