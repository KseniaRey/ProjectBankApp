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
@Table(name = "agreements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "account_id") // у нас уже есть такая колонка в другой таблице - норм же?
    private UUID accountId; // UUID или Integer?
    @Column(name = "product_id")
    private int productId;
    @Column(name = "interest_rate")
    private double interestRate;
    @Column(name = "status")
    private int status;
    @Column(name = "sum")
    private BigDecimal sum; // сумма чего и нужен ли нам биг децимал?
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
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
