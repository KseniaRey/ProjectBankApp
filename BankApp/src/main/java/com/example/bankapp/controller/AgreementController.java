package com.example.bankapp.controller;

import com.example.bankapp.entity.Agreement;
import com.example.bankapp.service.AgreementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/agreement")
public class AgreementController {
    private final AgreementService agreementService;

    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @PutMapping ("/{id}")
    public ResponseEntity<String> updateAgreementById(@PathVariable UUID id, @RequestBody Agreement updatedAgreement){
      agreementService.updateById(id, updatedAgreement);
        return ResponseEntity.ok("Entity with id: " + id + " updated");
    }
}
