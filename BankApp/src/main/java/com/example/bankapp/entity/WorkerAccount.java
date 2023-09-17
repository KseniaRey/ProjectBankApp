package com.example.bankapp.entity;

import com.example.bankapp.enums.WorkerAccountRole;
import com.example.bankapp.enums.WorkerAccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "worker_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WorkerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "worker_account_status")
    private WorkerAccountStatus status;

    @Column(name = "worker_account_role")
    private WorkerAccountRole role;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "managers_account", referencedColumnName = "id")
    private Manager manager;
}
