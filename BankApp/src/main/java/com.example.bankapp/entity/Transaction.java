package com.example.bankapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GenericGenerator(name = "UUID", strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(name = "debit_account_id")
    private UUID debitAccountId;
    @Column(name = "credit_account_id")
    private UUID creditAccountId;
    @Column(name = "transaction_type")
    private int type;
    @Column(name = "transaction_amount")
    private double amount;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
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
