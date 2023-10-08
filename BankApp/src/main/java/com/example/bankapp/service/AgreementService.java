package com.example.bankapp.service;

import com.example.bankapp.entity.Agreement;

import java.util.UUID;

public interface AgreementService {
    void updateById(UUID id, Agreement updatedAgreement);
}
