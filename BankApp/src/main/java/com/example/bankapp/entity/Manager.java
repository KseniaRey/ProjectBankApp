package com.example.bankapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "managers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "manager_status") // // ЕНАМ!!!
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY) // many потому что лист
    @JoinColumn(name = "clients_list", referencedColumnName = "id")
    private List<Client> clients;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY) // CHANGE OTHER CLASS
    @JoinColumn(name = "managers_account", referencedColumnName = "id")
    private WorkerAccount account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager manager)) return false;
        return Objects.equals(id, manager.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
