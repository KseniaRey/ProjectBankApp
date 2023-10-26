package com.example.bankapp.service.impl;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.User;
import com.example.bankapp.enums.AccountType;
import com.example.bankapp.enums.Currency;
import com.example.bankapp.enums.Status;
import com.example.bankapp.mapper.AccountMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.UserRepository;
import com.example.bankapp.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
    }
    @Override
    @Transactional
    public Account createAccount(AccountDto accountDto){
        Account account = accountMapper.toAccountEntity(accountDto); // дописать метод в маппере и в маппер перенести 36-41 строки
        setCreatedValuesInAccount(accountDto, account);
        account.setUpdatedAt(LocalDateTime.now());
        User user = userRepository.findById(UUID.fromString(accountDto.getClientId())).orElse(null);
        account.setClient(user);
        accountRepository.save(account);
        return account;
    }

    private static void setCreatedValuesInAccount(AccountDto accountDto, Account account) {
        account.setName(accountDto.getName());
        account.setType(AccountType.valueOf(accountDto.getType()));
        account.setStatus(Status.valueOf(accountDto.getStatus()));
        account.setBalance(new BigDecimal((accountDto.getBalance()))); // изменила вот тут parselLong на new BigDecimal из-за ошибки
        account.setCurrencyCode(Currency.valueOf(accountDto.getCurrencyCode()));
        account.setCreatedAt(LocalDateTime.now());
    }

    @Override
    @Transactional
    public List<AccountDto> getByProductName(String productName) {
        List<Account> accounts = accountRepository.getByProductName(productName);
        return accountMapper.accountsToAccountDto(accounts);
    }
}
