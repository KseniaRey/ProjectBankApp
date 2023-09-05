package com.example.bankapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {
    private UUID id;
    private UUID debitAccountId;
    private UUID creditAccountId;
    private int type;
    private double amount;
    private String description;
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(debitAccountId, that.debitAccountId) && Objects.equals(creditAccountId, that.creditAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debitAccountId, creditAccountId);
    }
}
