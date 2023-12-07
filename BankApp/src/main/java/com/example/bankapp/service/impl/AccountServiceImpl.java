package com.example.bankapp.service.impl;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.mapper.AccountMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.UserRepository;
import com.example.bankapp.security.UserProvider;
import com.example.bankapp.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final UserProvider userProvider;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, AccountMapper accountMapper, UserProvider userProvider) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
        this.userProvider = userProvider;
    }
    @Override
    @Transactional
    public AccountDto createAccount(AccountDto accountDto){
        Account account = accountMapper.toAccountEntity(accountDto);
        account.setUpdatedAt(LocalDateTime.now());
        account.setCreatedAt(LocalDateTime.now());
        account.setClient(userProvider.getCurrentUser());
        accountRepository.save(account);
        return accountMapper.toAccountDto(account);
    }

    @Override
    @Transactional
    public List<AccountDto> getByProductName(String productName) {
        List<Account> accounts = accountRepository.getByProductName(productName);
        return accountMapper.accountsToAccountDto(accounts);
    }

    @Override
    @Transactional
    public AccountDto getById(String accountId) {
        Account account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(()-> new EntityNotFoundException("Entity is not found"));
        return accountMapper.toAccountDto(account);
    }
}
