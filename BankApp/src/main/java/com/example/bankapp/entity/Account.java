package com.example.bankapp.entity;

import com.example.bankapp.enums.AccountType;
import com.example.bankapp.enums.Currency;
import com.example.bankapp.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "account_name")
    private String name;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "account_balance")
    private BigDecimal balance;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private Currency currencyCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;

    @OneToMany(mappedBy = "debitAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Transaction> debitTransactions = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "creditAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> creditTransactions = new HashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Agreement agreement;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
