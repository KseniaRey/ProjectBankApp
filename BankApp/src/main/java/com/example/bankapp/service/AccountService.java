package com.example.bankapp.service;

import com.example.bankapp.dto.AccountDto;

import java.util.List;

public interface AccountService{
    AccountDto createAccount(AccountDto accountDto);

    List<AccountDto> getByProductName(String productName);
}
