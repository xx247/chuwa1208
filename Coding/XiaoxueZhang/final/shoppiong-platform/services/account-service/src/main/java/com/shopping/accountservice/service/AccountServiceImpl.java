package com.shopping.accountservice.service;

import com.shopping.accountservice.dto.*;
import com.shopping.accountservice.entity.*;
import java.util.UUID;
import com.shopping.accountservice.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserAccountRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        if (userRepo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        UserAccount user = UserAccount.builder()
                .email(email)
                .username(request.getUsername().trim())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        if (request.getShippingAddress() != null) {
            user.getAddresses().add(toAddress(request.getShippingAddress(), AddressType.SHIPPING, user));
        }
        if (request.getBillingAddress() != null) {
            user.getAddresses().add(toAddress(request.getBillingAddress(), AddressType.BILLING, user));
        }

        UserAccount saved = userRepo.save(user);

        return RegisterResponse.builder()
                .userId(saved.getId())
                .email(saved.getEmail())
                .username(saved.getUsername())
                .build();
    }

    private Address toAddress(AddressDto dto, AddressType type, UserAccount user) {
        return Address.builder()
                .type(type)
                .line1(dto.getLine1().trim())
                .line2(dto.getLine2() == null ? null : dto.getLine2().trim())
                .city(dto.getCity().trim())
                .state(dto.getState().trim())
                .zip(dto.getZip().trim())
                .country(dto.getCountry().trim())
                .user(user)
                .build();
    }


    @Override
    public AccountResponse getMe(String userId) {
        UserAccount user = userRepo.findById(UUID.fromString(userId))
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        return toAccountResponse(user);
    }

    @Override
    public AccountResponse updateMe(String userId, UpdateAccountRequest request) {
        UserAccount user = userRepo.findById(UUID.fromString(userId))
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        user.setUsername(request.getUsername().trim());
        user.setUpdatedAt(Instant.now());

        upsertAddress(user, AddressType.SHIPPING, request.getShippingAddress());
        upsertAddress(user, AddressType.BILLING, request.getBillingAddress());

        UserAccount saved = userRepo.save(user);
        return toAccountResponse(saved);
    }

    private void upsertAddress(UserAccount user, AddressType type, AddressDto dto) {
        if (dto == null) return; // keep simple: ignore null (no delete)

        Address address = user.getAddresses().stream()
                .filter(a -> a.getType() == type)
                .findFirst()
                .orElseGet(() -> {
                    Address a = Address.builder().type(type).user(user).build();
                    user.getAddresses().add(a);
                    return a;
                });

        address.setLine1(dto.getLine1().trim());
        address.setLine2(dto.getLine2() == null ? null : dto.getLine2().trim());
        address.setCity(dto.getCity().trim());
        address.setState(dto.getState().trim());
        address.setZip(dto.getZip().trim());
        address.setCountry(dto.getCountry().trim());
    }

    private AccountResponse toAccountResponse(UserAccount user) {
        AddressDto shipping = user.getAddresses().stream()
                .filter(a -> a.getType() == AddressType.SHIPPING)
                .findFirst()
                .map(this::toDto)
                .orElse(null);

        AddressDto billing = user.getAddresses().stream()
                .filter(a -> a.getType() == AddressType.BILLING)
                .findFirst()
                .map(this::toDto)
                .orElse(null);

        return AccountResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .shippingAddress(shipping)
                .billingAddress(billing)
                .build();
    }

    private AddressDto toDto(Address a) {
        AddressDto dto = new AddressDto();
        dto.setLine1(a.getLine1());
        dto.setLine2(a.getLine2());
        dto.setCity(a.getCity());
        dto.setState(a.getState());
        dto.setZip(a.getZip());
        dto.setCountry(a.getCountry());
        return dto;
    }

}