package com.example.bankapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class TransactionDto {
    private String id;
    private String debitAccountId;
    private String creditAccountId;
    private String type;
    private String amount;
    private String description;
    private String createdAt;

}
