package com.shopping.itemservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReserveResponse {
    private String itemId;
    private long reservedQuantity;
    private long remainingUnits;
    private boolean success;
    private String message;
}