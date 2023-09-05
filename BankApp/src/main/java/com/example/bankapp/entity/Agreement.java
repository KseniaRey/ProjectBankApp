package com.example.bankapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Agreement {
    private int id;
    private UUID accountId; // UUID или Integer?
    private int productId;
    private double interestRate;
    private int status;
    private BigDecimal sum; // сумма чего и нужен ли нам биг децимал?
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agreement agreement)) return false;
        return id == agreement.id && productId == agreement.productId && Objects.equals(accountId, agreement.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, productId);
    }
}
