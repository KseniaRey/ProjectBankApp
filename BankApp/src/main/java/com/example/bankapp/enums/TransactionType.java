package com.example.bankapp.enums;

public enum TransactionType {
    TRANSFER,
    INTERBANK_TRANSFER,
    PAYMENT,
    PURCHASE,
    CASH_WITHDRAW,
    DEPOSIT,
    FEE,
    REFUND,
    CREDIT,
    DEBIT,
    HOLD, // временная блокировка денег на счету (при аренде)
    AUTO_PAYMENT;
}
