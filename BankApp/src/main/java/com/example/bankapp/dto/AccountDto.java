package com.example.bankapp.dto;

import lombok.Data;
@Data
public class AccountDto {
    private String id;
    private String name;
    private String type;
    private String status;
    private String balance;
    private String currencyCode;
    private String productName;
    private String ownerFullName;
}
