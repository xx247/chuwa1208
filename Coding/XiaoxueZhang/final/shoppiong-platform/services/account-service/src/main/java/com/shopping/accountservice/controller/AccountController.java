package com.shopping.accountservice.controller;

import com.shopping.accountservice.dto.AccountResponse;
import com.shopping.accountservice.dto.UpdateAccountRequest;
import com.shopping.accountservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/me")
    public AccountResponse me(@AuthenticationPrincipal Jwt jwt) {
        return accountService.getMe(jwt.getSubject()); // subject = userId
    }

    @PutMapping("/me")
    public AccountResponse updateMe(@AuthenticationPrincipal Jwt jwt,
                                    @Valid @RequestBody UpdateAccountRequest request) {
        return accountService.updateMe(jwt.getSubject(), request);
    }
}