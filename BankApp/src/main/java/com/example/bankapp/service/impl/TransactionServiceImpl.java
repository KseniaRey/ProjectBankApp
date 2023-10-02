package com.example.bankapp.service.impl;

import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.enums.TransactionType;
import com.example.bankapp.mapper.TransactionMapper;
import com.example.bankapp.repository.TransactionRepository;
import com.example.bankapp.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    @Transactional
    public List<TransactionDto> getTransactionByType(String type) {
        List<Transaction> transactions = transactionRepository.findByType(TransactionType.valueOf(type));
        List<TransactionDto> transactionsDto = transactionMapper.transactonsToTransactionsDTO(transactions);
        return transactionsDto;
    }
}
