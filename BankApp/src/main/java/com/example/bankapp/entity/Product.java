package com.example.bankapp.entity;

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
    private Integer id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "product_currency_code")
    @Enumerated(EnumType.STRING)
    private Currency currencyCode;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "product_limit")
    private Integer limit;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "productId", cascade = ALL, fetch = FetchType.LAZY)
    private Set<Agreement> agreements = new HashSet<>();

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
