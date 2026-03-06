package com.shopping.accountservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountResponse {
    private UUID userId;
    private String email;
    private String username;
    private AddressDto shippingAddress;
    private AddressDto billingAddress;
}