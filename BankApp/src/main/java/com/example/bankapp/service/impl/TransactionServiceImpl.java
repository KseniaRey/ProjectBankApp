package com.example.bankapp.service.impl;

import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.enums.TransactionType;
import com.example.bankapp.mapper.TransactionMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.TransactionRepository;
import com.example.bankapp.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository; // репо счетов

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public List<TransactionDto> getTransactionByType(String type) {
        List<Transaction> transactions = transactionRepository.findByType(TransactionType.valueOf(type));
        List<TransactionDto> transactionsDto = transactionMapper.transactonsToTransactionsDTO(transactions);
        return transactionsDto;
    }

    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
    Transaction transaction = new Transaction();

    String debitAccountId = transactionDto.getDebitAccountId();
    Account debitAccount = accountRepository.findById(UUID.fromString(debitAccountId)).orElse(null);
    if (debitAccount == null){
        throw new RuntimeException("Debit Account is null");
    }
    String creditAccountId = transactionDto.getCreditAccountId();
    Account creditAccount = accountRepository.findById(UUID.fromString(creditAccountId)).orElse(null);
    transaction.setDebitAccount(debitAccount);
    transaction.setCreditAccount(creditAccount);
    transaction.setType(TransactionType.valueOf(transactionDto.getType())); // какой тип создастся?
    transaction.setAmount(new BigDecimal(transactionDto.getAmount()));
    transaction.setDescription(transactionDto.getDescription());
    return transaction;
    }
}
