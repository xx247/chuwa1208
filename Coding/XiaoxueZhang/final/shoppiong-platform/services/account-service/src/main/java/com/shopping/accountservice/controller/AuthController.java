package com.shopping.accountservice.controller;

import com.shopping.accountservice.dto.LoginRequest;
import com.shopping.accountservice.dto.LoginResponse;
import com.shopping.accountservice.dto.RegisterRequest;
import com.shopping.accountservice.dto.RegisterResponse;
import com.shopping.accountservice.service.AccountService;
import com.shopping.accountservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return accountService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}