package com.shopping.itemservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class UpdateInventoryRequest {
    @NotBlank
    private String itemId;

    @PositiveOrZero
    private long availableUnits;
}