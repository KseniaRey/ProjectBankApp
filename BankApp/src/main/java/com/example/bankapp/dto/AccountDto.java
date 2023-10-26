package com.example.bankapp.dto;

import com.example.bankapp.enums.AccountType;
import com.example.bankapp.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class AccountDto {
    private String id;
    private String name;
    private String type;
    private String status;
    private String balance;
    private String currencyCode;
    private String clientId; // генерируется и будет передано в дто, верно же?
}
