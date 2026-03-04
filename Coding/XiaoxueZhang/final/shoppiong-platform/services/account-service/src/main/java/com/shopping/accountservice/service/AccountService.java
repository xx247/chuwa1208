package com.shopping.accountservice.service;

import com.shopping.accountservice.dto.RegisterRequest;
import com.shopping.accountservice.dto.RegisterResponse;

public interface AccountService {
    RegisterResponse register(RegisterRequest request);
}