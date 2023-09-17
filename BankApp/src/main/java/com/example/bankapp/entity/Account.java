package com.example.bankapp.entity;

import com.example.bankapp.enums.AccountStatus;
import com.example.bankapp.enums.AccountType;
import com.example.bankapp.enums.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "account_name")
    private String name;

    @Column(name = "account_type")
    private AccountType type;

    @Column(name = "account_status") // ЕНАМ!!!
    private AccountStatus status;

    @Column(name = "account_balance")
    private BigDecimal balance;

    @Column(name = "currency_code") // ЕНАМ!!!
    private Currency currencyCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_account", referencedColumnName = "id")
    private Client client;

    @OneToMany(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "agreementsList", referencedColumnName = "id") // проверить правильно ли по  акк айди
    // вот тут вопрос - пришлось заменить на айди потому что была ошибка, но по какому айди он сейчас проверяет?
    private Set<Agreement> agreementList;

    @OneToMany(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    private Set<Transaction> debit_transaction;

    @OneToMany(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    private Set<Transaction> credit_transaction;

    // если мы создаем связь от акк к транзакции 1->..., то как в транзакции показать? По 2 полям ...->1?
    // ну и зачем нам тогда аккаунт в транзакции, блин?!

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
