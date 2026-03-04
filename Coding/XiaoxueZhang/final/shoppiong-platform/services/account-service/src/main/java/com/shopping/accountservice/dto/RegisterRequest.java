package com.shopping.accountservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @Email @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8, max = 72)
    private String password;

    @Valid
    private AddressDto shippingAddress;

    @Valid
    private AddressDto billingAddress;
}