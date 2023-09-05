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
public class Account {
    private UUID id;
    private UUID clientId;
    private String name;
    private int type; // ?
    private int status;
    private BigDecimal balance;
    private int currencyCode; // что такое?
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(id, account.id) && Objects.equals(clientId, account.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId);
    }
}
