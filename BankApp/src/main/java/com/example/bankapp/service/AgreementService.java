package com.example.bankapp.service;

import com.example.bankapp.dto.AgreementDto;

import java.util.UUID;

public interface AgreementService {
    AgreementDto updateById(UUID id, AgreementDto updatedAgreementDto);

    AgreementDto createAgreement(AgreementDto agreementDto);
}
