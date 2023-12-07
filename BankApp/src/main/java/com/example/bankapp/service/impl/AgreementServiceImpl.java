package com.example.bankapp.service.impl;

import com.example.bankapp.dto.AgreementDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Agreement;
import com.example.bankapp.entity.Product;
import com.example.bankapp.entity.User;
import com.example.bankapp.mapper.AgreementMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.AgreementRepository;
import com.example.bankapp.repository.ProductRepository;
import com.example.bankapp.repository.UserRepository;
import com.example.bankapp.service.AgreementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final UserRepository userRepository;
    private final AgreementMapper agreementMapper;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    public AgreementServiceImpl(AgreementRepository agreementRepository, UserRepository userRepository, AgreementMapper agreementMapper, AccountRepository accountRepository, ProductRepository productRepository) {
        this.agreementRepository = agreementRepository;
        this.userRepository = userRepository;
        this.agreementMapper = agreementMapper;
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public AgreementDto updateById(UUID id, AgreementDto updatedAgreementDto) {
        Agreement existingAgreement = agreementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " not found"));
        if (updatedAgreementDto.getManagerId() != null &&
                !existingAgreement.getManager().getId().toString().equals(updatedAgreementDto.getManagerId())) {
            User manager = userRepository.findManagerById(UUID.fromString(updatedAgreementDto.getManagerId()))
                    .orElseThrow(() -> new EntityNotFoundException("Manager is not found"));
            existingAgreement.setManager(manager);
        }
        agreementMapper.updateAgreementFromDto(updatedAgreementDto, existingAgreement);
        existingAgreement.setUpdatedAt(LocalDateTime.now());
        agreementRepository.save(existingAgreement);
        return agreementMapper.mapToDto(existingAgreement);
    }

    @Transactional
    @Override
    public AgreementDto createAgreement(AgreementDto agreementDto) {
        Agreement agreement = agreementMapper.mapToEntity(agreementDto);
        Account account = accountRepository.findById(UUID.fromString(agreementDto.getAccountId()))
                .orElseThrow(() -> new EntityNotFoundException("Account is not found"));
        Product product = productRepository.findById(Integer.valueOf(agreementDto.getProductId()))
                .orElseThrow(() -> new EntityNotFoundException("Product is not found"));
        User manager = userRepository.findById(UUID.fromString(agreementDto.getManagerId()))
                .orElseThrow(() -> new EntityNotFoundException("Manager is not found"));

        agreement.setAccount(account);
        agreement.setProduct(product);
        agreement.setManager(manager);
        agreement.setSum(BigDecimal.valueOf(product.getMinLimit()));
        agreement.setCreatedAt(LocalDateTime.now());
        agreement.setUpdatedAt(LocalDateTime.now());
        agreementRepository.save(agreement);
        return agreementMapper.mapToDto(agreement);
    }
}
