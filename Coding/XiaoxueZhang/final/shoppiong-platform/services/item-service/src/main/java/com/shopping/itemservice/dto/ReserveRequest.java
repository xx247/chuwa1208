package com.shopping.itemservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ReserveRequest {
    @NotBlank
    private String itemId;

    @Positive
    private long quantity;
}