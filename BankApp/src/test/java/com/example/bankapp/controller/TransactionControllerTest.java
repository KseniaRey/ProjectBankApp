package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.dto.ErrorData;
import com.example.bankapp.dto.TransactionDto;
import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/db/drop-tables.sql")
@Sql("/db/create-tables.sql")
@Sql("/db/insert-test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithUserDetails(value = "john@example.com")
    public void testGetTransactionByType() throws Exception {
        List<TransactionDto> transactionDtos = getExpTransferTypeTransaction();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/transaction/by-type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("type", "TRANSFER")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andReturn();

        String transactionResult = mvcResult.getResponse().getContentAsString();

        List<TransactionDto> actualTransaction = objectMapper.readValue(transactionResult, new TypeReference<>() {
        });

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals(actualTransaction, transactionDtos);

    }

    @Test
    @WithUserDetails(value = "john@example.com")
    void createTransaction() throws Exception {
        TransactionDto transactionDto = getTransactionDto();

        String getCreditAccountBeforeTransactionJson = getAccountById(transactionDto.getCreditAccountId());
        String getDebitAccountBeforeTransactionJson = getAccountById(transactionDto.getDebitAccountId());

        String transactionDtoJson = objectMapper.writeValueAsString(transactionDto);
        MvcResult transactionResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/transaction/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionDtoJson))
                .andExpect(status().isCreated())
                .andReturn();
        String getCreditAccountAfterTransactionJson = getAccountById(transactionDto.getCreditAccountId());
        String getDebitAccountAfterTransactionJson = getAccountById(transactionDto.getDebitAccountId());

        AccountDto creditAccountDtoBeforeTransaction = objectMapper.readValue(getCreditAccountBeforeTransactionJson, AccountDto.class);
        AccountDto debitAccountDtoBeforeTransaction = objectMapper.readValue(getDebitAccountBeforeTransactionJson, AccountDto.class);
        TransactionDto resultDto = objectMapper.readValue(transactionResult.getResponse().getContentAsString(), TransactionDto.class);
        AccountDto creditAccountDtoAfterTransaction = objectMapper.readValue(getCreditAccountAfterTransactionJson, AccountDto.class);
        AccountDto debitAccountDtoAfterTransaction = objectMapper.readValue(getDebitAccountAfterTransactionJson, AccountDto.class);

        BigDecimal expectedBalanceOfCreditAccountAfterTransaction = new BigDecimal(creditAccountDtoBeforeTransaction.getBalance()).add(new BigDecimal(transactionDto.getAmount()));
        BigDecimal expectedBalanceOfDebitAccountAfterTransaction = new BigDecimal(debitAccountDtoBeforeTransaction.getBalance()).subtract(new BigDecimal(transactionDto.getAmount()));

        Assertions.assertNotNull(resultDto.getId());
        Assertions.assertNotNull(resultDto.getCreatedAt());
        resultDto.setId(null);
        resultDto.setCreatedAt(null);
        Assertions.assertEquals(transactionDto, resultDto);
        Assertions.assertEquals(expectedBalanceOfCreditAccountAfterTransaction.toString(), creditAccountDtoAfterTransaction.getBalance());
        Assertions.assertEquals(expectedBalanceOfDebitAccountAfterTransaction.toString(), debitAccountDtoAfterTransaction.getBalance());
    }

    @Test
    @WithUserDetails(value = "john@example.com")
    void testNegativeCaseWhileTransaction() throws Exception {
        TransactionDto transactionDto = getTransactionDto();
        transactionDto.setAmount("10000000000000");

        String transactionDtoJson = objectMapper.writeValueAsString(transactionDto);
        String transactionResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/transaction/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionDtoJson))
                .andExpect(status().is(406))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ErrorData errorResult = objectMapper.readValue(transactionResult, ErrorData.class);
        Assertions.assertEquals("Not enough money", errorResult.getMessage());

    }

    @Test
    @WithUserDetails(value = "john@example.com")
    void testNegativeCaseRuntimeWhileTransaction() throws Exception {
        TransactionDto transactionDto = getTransactionDto();
        transactionDto.setType("invalid_type");

        String transactionDtoJson = objectMapper.writeValueAsString(transactionDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/transaction/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionDtoJson))
                .andExpect(status().is(500));

    }

    private static TransactionDto getTransactionDto() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setCreditAccountId("a16b4e7d-8a29-4327-b79c-4f6b21410d12");
        transactionDto.setDebitAccountId("2442b573-307c-4f39-994e-5bb359745cba");
        transactionDto.setType("PURCHASE");
        transactionDto.setAmount("100.00");
        transactionDto.setDescription("for ice cream");
        return transactionDto;
    }

    private String getAccountById(String accountId) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .get("/account/" + accountId))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private static List<TransactionDto> getExpTransferTypeTransaction() {
        List<TransactionDto> transactionDtos = new ArrayList<>();
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId("01fd4094-d09a-473d-a389-728abda7d813");
        transactionDto.setDebitAccountId("a16b4e7d-8a29-4327-b79c-4f6b21410d12");
        transactionDto.setCreditAccountId("a16b4e7d-8a29-4327-b79c-4f6b21410d12");
        transactionDto.setType("TRANSFER");
        transactionDto.setAmount("500.0000");
        transactionDto.setDescription("Transfer from Savings to Checking");
        transactionDto.setCreatedAt("2023-09-17T12:00:00");
        transactionDtos.add(transactionDto);
        return transactionDtos;
    }
}