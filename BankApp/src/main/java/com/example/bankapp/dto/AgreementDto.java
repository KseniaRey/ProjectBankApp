package com.example.bankapp.dto;

import lombok.Data;

@Data
public class AgreementDto {
    private String id;
    private String accountId;
    private String productId;
    private String managerId;
    private String interestRate;
    private String status;
    private String sum;
}
