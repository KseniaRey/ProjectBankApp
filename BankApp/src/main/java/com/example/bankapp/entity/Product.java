package com.example.bankapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "manager_id")
    private int managerId; // убрать связь с менеджером - тут убрать?

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_status")
    private int status;

    @Column(name = "product_currency_code")
    private int currencyCode;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "product_limit")
    private int limit;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id", referencedColumnName = "id")
    private Manager manager;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_agreement", referencedColumnName = "product_id")
    private Agreement agreement;
//    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "client", referencedColumnName = "id")
//    private Client client;
    // -- нужно ли нам вот это - закомментированное? Зачем нам экз клиента в продукте? По идее нет, но можем ли мы строить связь в одну сторону?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id == product.id && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
