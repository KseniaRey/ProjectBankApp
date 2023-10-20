package com.example.bankapp.service.impl;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.User;
import com.example.bankapp.enums.AccountType;
import com.example.bankapp.enums.Currency;
import com.example.bankapp.enums.Status;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.UserRepository;
import com.example.bankapp.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public Account createAccount(AccountDto accountDto){
        Account account = new Account();
        account.setName(accountDto.getName());
        account.setType(AccountType.valueOf(accountDto.getType()));
        account.setStatus(Status.valueOf(accountDto.getStatus()));
        account.setBalance(BigDecimal.valueOf(Long.parseLong(accountDto.getBalance())));
        account.setCurrencyCode(Currency.valueOf(accountDto.getCurrencyCode()));
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        User user = userRepository.findById(UUID.fromString(accountDto.getClientId())).orElse(null);
        account.setClient(user);
        accountRepository.save(account);
        return account;
    }
}
