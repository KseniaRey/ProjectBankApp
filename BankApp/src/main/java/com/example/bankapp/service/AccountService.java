package com.example.bankapp.service;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;

public interface AccountService{
    Account createAccount(AccountDto accountDto);
}
