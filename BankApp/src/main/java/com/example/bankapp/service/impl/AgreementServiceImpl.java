package com.example.bankapp.service.impl;

import com.example.bankapp.entity.Agreement;
import com.example.bankapp.repository.AgreementRepository;
import com.example.bankapp.service.AgreementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;

    public AgreementServiceImpl(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }
    @Transactional
    @Override
    public void updateById(UUID id, Agreement updatedAgreement) {
        Agreement existingAgreement = agreementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " not found"));
            existingAgreement.setSum(updatedAgreement.getSum());
            existingAgreement.setStatus(updatedAgreement.getStatus());
            existingAgreement.setInterestRate(updatedAgreement.getInterestRate());
            existingAgreement.setUpdatedAt(LocalDateTime.now());
    }
}
