package com.example.bankapp.service.impl;

import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.enums.TransactionType;
import com.example.bankapp.exceptions.NotEnoughMoneyException;
import com.example.bankapp.mapper.TransactionMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.TransactionRepository;
import com.example.bankapp.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;

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
    @Transactional
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toTransactionEntity(transactionDto);
        Account debitAccount = updateDebitAccount(transactionDto, transaction);
        Account creditAccount = updateCreditAccount(transactionDto, transaction);
        transaction.setDebitAccount(debitAccount);
        transaction.setCreditAccount(creditAccount);
        transaction.setCreatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
        return transactionMapper.toTransactionDto(transaction);
    }

    private Account updateCreditAccount(TransactionDto transactionDto, Transaction transaction) {
        String creditAccountId = transactionDto.getCreditAccountId();
        Account creditAccount = accountRepository.findById(UUID.fromString(creditAccountId))
                .orElseThrow(() -> new EntityNotFoundException("Account entity is not found"));
        BigDecimal creditBalance = creditAccount.getBalance().add(transaction.getAmount());
        creditAccount.setBalance(creditBalance);
        creditAccount.setUpdatedAt(LocalDateTime.now());
        accountRepository.save(creditAccount);
        return creditAccount;
    }

    private Account updateDebitAccount(TransactionDto transactionDto, Transaction transaction) {
        String debitAccountId = transactionDto.getDebitAccountId();
        Account debitAccount = accountRepository.findById(UUID.fromString(debitAccountId))
                .orElseThrow(() -> new EntityNotFoundException("Debit Account is null"));
        if (debitAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
            throw new NotEnoughMoneyException("Not enough money");
        }
        BigDecimal debitBalance = debitAccount.getBalance().subtract(transaction.getAmount());
        debitAccount.setBalance(debitBalance);
        debitAccount.setUpdatedAt(LocalDateTime.now());
        accountRepository.save(debitAccount);
        return debitAccount;
    }
}
