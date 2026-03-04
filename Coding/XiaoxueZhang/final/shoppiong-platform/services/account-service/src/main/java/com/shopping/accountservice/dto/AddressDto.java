package com.shopping.accountservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressDto {
    @NotBlank private String line1;
    private String line2;
    @NotBlank private String city;
    @NotBlank private String state;
    @NotBlank private String zip;
    @NotBlank private String country;
}