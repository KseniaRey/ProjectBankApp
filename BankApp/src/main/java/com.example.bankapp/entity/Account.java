package com.example.bankapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    // или вот тут onetomany to trans - debit and credit?
    private UUID id;
    @Column(name = "client_id")
    private UUID clientId;
    @Column(name = "account_name")
    private String name;
    @Column(name = "account_type")
    private int type;
    @Column(name = "account_status")
    private int status;
    @Column(name = "account_balance")
    private BigDecimal balance;
    @Column(name = "currency_code")
    private int currencyCode; // что такое?
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
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
