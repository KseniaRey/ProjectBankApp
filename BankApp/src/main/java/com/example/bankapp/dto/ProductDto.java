package com.example.bankapp.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String status;
    private String currencyCode;
    private String interestRate;
    private String minLimit;

}
