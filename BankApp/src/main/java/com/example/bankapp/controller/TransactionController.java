package com.example.bankapp.controller;

import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/transaction")
public class                                                                                                                                                                                                                                          TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping("/by-type")
    public List<TransactionDto> getTransactionByType(@RequestParam(name = "type") String type){
        return transactionService.getTransactionByType(type);
    }
    @PostMapping("/create")
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto){
        return transactionService.createTransaction(transactionDto);
    }
}
