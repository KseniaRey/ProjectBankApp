package com.example.bankapp.service;

import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getTransactionByType(String type);

    Transaction createTransaction(TransactionDto transactionDto);
}
