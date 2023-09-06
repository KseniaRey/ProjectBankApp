package com.example.bankapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
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
    @Column(name = "manager_id")
    private int managerId;
    @Column(name = "status")
    private int status;
    @Column(name = "tax_code")
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
    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY) // стырила строчку - объединять, сохранять и обновлять
    @JoinColumn(name = "account_id", referencedColumnName = "client_id") // правильно связала?
    private Account account;
    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "clients_list") // правильно связала?
    private Manager manager;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return managerId == client.managerId && Objects.equals(id, client.id) && Objects.equals(account, client.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, managerId, account);
    }
}