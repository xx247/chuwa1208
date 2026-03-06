package com.shopping.accountservice.service;

import com.shopping.accountservice.dto.LoginRequest;
import com.shopping.accountservice.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}