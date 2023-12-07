package com.example.bankapp.controller;

import com.example.bankapp.dto.AgreementDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/db/drop-tables.sql")
@Sql("/db/create-tables.sql")
@Sql("/db/insert-test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
class AgreementControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithUserDetails(value = "alice@example.com")
    public void testUpdateAgreementById() throws Exception {
        String agreementId = "6cf469b4-a094-47c2-bfd2-3c3bf4742436";
        String expectedIR = "3.5000";
        AgreementDto agreementDto = new AgreementDto();
        agreementDto.setManagerId("4ede8454-d2ab-4143-a0e2-4f196ec25dbd");
        agreementDto.setStatus("SUSPENDED");
        agreementDto.setSum("100000");

        String agreementDtoUpdatedJson = objectMapper.writeValueAsString(agreementDto);

        String updatedAgreementResultString = mockMvc.perform(MockMvcRequestBuilders.put("/agreement/" + agreementId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agreementDtoUpdatedJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AgreementDto result = objectMapper.readValue(updatedAgreementResultString, AgreementDto.class);

        Assertions.assertEquals(agreementId, result.getId());
        Assertions.assertEquals(agreementDto.getManagerId(), result.getManagerId());
        Assertions.assertEquals(expectedIR, result.getInterestRate());
        Assertions.assertEquals(agreementDto.getStatus(), result.getStatus());
        Assertions.assertEquals(agreementDto.getSum(), result.getSum());
    }

    @Test
    @WithUserDetails(value = "john@example.com")
    public void testCreateAgreement() throws Exception {
        AgreementDto agreementDto = new AgreementDto();
        agreementDto.setAccountId("2442b573-307c-4f39-994e-5bb359745cba");
        agreementDto.setProductId("1");
        agreementDto.setManagerId("5507c281-154a-4dd1-ba8e-6b32ff4676d0");
        agreementDto.setStatus("ACTIVE");

        String createAgreementRequestJson = objectMapper.writeValueAsString(agreementDto);

        String createdAgreementResultString = mockMvc.perform(MockMvcRequestBuilders.post("/agreement/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createAgreementRequestJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AgreementDto resultCreationAgreement = objectMapper.readValue(createdAgreementResultString, AgreementDto.class);
        Assertions.assertNotNull(resultCreationAgreement.getId());
        Assertions.assertNotNull(resultCreationAgreement.getSum());
        Assertions.assertEquals(agreementDto.getAccountId(), resultCreationAgreement.getAccountId());
        Assertions.assertEquals(agreementDto.getProductId(), resultCreationAgreement.getProductId());
        Assertions.assertEquals(agreementDto.getManagerId(), resultCreationAgreement.getManagerId());
        Assertions.assertEquals(agreementDto.getStatus(), resultCreationAgreement.getStatus());
    }
}