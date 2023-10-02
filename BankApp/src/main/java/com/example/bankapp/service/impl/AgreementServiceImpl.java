package com.example.bankapp.service.impl;

import com.example.bankapp.entity.Agreement;
import com.example.bankapp.repository.AgreementRepository;
import com.example.bankapp.service.AgreementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;

    public AgreementServiceImpl(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }
    @Transactional
    @Override
    public Agreement updateById(UUID id, Agreement updatedAgreement) {
        Agreement existingAgreement = agreementRepository.findById(id).orElse(null);

        if (existingAgreement != null){
            existingAgreement.setAccount(updatedAgreement.getAccount());
            existingAgreement.setId(updatedAgreement.getId());
            existingAgreement.setManager(updatedAgreement.getManager());
            existingAgreement.setSum(updatedAgreement.getSum());
            existingAgreement.setStatus(updatedAgreement.getStatus());
            existingAgreement.setProductId(updatedAgreement.getProductId());
            existingAgreement.setInterestRate(updatedAgreement.getInterestRate());
            existingAgreement.setUpdatedAt(updatedAgreement.getUpdatedAt());
            existingAgreement.setCreatedAt(updatedAgreement.getCreatedAt());

            return agreementRepository.save(existingAgreement);
        } else {
            throw new EntityNotFoundException(id + "not found");
        }
    }
}
