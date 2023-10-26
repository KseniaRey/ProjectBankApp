package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Product;
import com.example.bankapp.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/create")
    public Account createAccount(@RequestBody AccountDto accountDto){
        return accountService.createAccount(accountDto);
    }
    @GetMapping("/get-by-productName")
    public List<AccountDto> getByProductName(@RequestParam String productName){
        return accountService.getByProductName(productName);
    }
}
