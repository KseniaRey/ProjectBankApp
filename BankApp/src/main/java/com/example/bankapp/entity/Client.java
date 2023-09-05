package com.example.bankapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
@ToString
class Client{
    private UUID id;
    private int managerId;
    private int status;
    private String taxCode;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Account account;

    // доделать из редми

    // переопределить иквалс и хешкод по тем полям, которые сделают из него уникального клиента


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