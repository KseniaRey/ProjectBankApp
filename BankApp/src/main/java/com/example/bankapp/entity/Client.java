package com.example.bankapp.entity;

import com.example.bankapp.enums.ClientsStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "status")
    private ClientsStatus status;

    @Column(name = "tax_code") // ИНН
    private String taxCode;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "adress")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "clients_account", referencedColumnName = "client_id")
    private Account account;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "clients_manager", referencedColumnName = "id")
    private Manager manager;

    @OneToMany(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "clients_products", referencedColumnName = "id") // ref - is id of the product to know which service has been added
    private List<Product> productList; // лист услуг клиента, которые у него есть и которые у него были.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return manager == client.manager && Objects.equals(id, client.id) && Objects.equals(account, client.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manager, account);
    }
}