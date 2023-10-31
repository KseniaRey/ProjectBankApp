package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('MANAGER')") // - для проверки работы секьюрити. Доступ только менеджеру
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        AccountDto result = accountService.createAccount(accountDto);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping("/get-by-productName")
    public List<AccountDto> getByProductName(@RequestParam(name = "productName") String productName){
        return accountService.getByProductName(productName);
    }
}
