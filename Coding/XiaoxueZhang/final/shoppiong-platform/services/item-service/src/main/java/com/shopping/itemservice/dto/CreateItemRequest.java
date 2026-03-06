package com.shopping.itemservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class CreateItemRequest {
    @NotBlank
    private String itemId;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal unitPrice;

    @NotBlank
    private String upc;

    private List<String> pictureUrls;

    @PositiveOrZero
    private long availableUnits;

    private Map<String, Object> attributes;
}