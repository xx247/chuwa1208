package com.shopping.accountservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAccountRequest {
    @NotBlank
    private String username;

    @Valid
    private AddressDto shippingAddress;

    @Valid
    private AddressDto billingAddress;
}