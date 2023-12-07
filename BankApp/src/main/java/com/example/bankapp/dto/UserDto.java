package com.example.bankapp.dto;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    private String id;
    private String status;
    private String taxCode;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String role;
}
