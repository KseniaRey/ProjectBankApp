package com.example.bankapp.controller;

import com.example.bankapp.dto.AgreementDto;
import com.example.bankapp.service.AgreementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/agreement")
public class AgreementController {
    private final AgreementService agreementService;

    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @PutMapping ("/{id}")
    public ResponseEntity<AgreementDto> updateAgreementById(@PathVariable UUID id, @RequestBody AgreementDto updatedAgreementDto){
      AgreementDto result = agreementService.updateById(id, updatedAgreementDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<AgreementDto> createAgreement(@RequestBody AgreementDto agreementDto){
        AgreementDto result = agreementService.createAgreement(agreementDto);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
