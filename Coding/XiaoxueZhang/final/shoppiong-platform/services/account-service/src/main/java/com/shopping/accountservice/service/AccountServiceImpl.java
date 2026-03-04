package com.shopping.accountservice.service;

import com.shopping.accountservice.dto.AddressDto;
import com.shopping.accountservice.dto.RegisterRequest;
import com.shopping.accountservice.dto.RegisterResponse;
import com.shopping.accountservice.entity.Address;
import com.shopping.accountservice.entity.AddressType;
import com.shopping.accountservice.entity.UserAccount;
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
}