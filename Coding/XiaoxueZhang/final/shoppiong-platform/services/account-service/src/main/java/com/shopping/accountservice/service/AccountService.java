package com.shopping.accountservice.service;

import com.shopping.accountservice.dto.AccountResponse;
import com.shopping.accountservice.dto.RegisterRequest;
import com.shopping.accountservice.dto.RegisterResponse;
import com.shopping.accountservice.dto.UpdateAccountRequest;

public interface AccountService {
    RegisterResponse register(RegisterRequest request);
    AccountResponse getMe(String userId);
    AccountResponse updateMe(String userId, UpdateAccountRequest request);
}