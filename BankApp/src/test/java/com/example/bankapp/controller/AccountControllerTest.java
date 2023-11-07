package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Sql("/db/drop-tables.sql")
@Sql("/db/create-tables.sql")
@Sql("/db/insert-test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @Autowired
    private AccountService accountService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithUserDetails(value = "alice@example.com")
    public void testCreateAccount() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setName("Alisia Smith");
        accountDto.setType("TRAVEL");
        accountDto.setStatus("NEW");
        accountDto.setBalance("20000");
        accountDto.setCurrencyCode("EUR");
        accountDto.setClientId("c586e826-f384-4c06-b00e-75ff679e732f");

        String accountDtoStrindData = objectMapper.writeValueAsString(accountDto);


        MvcResult accountCreatingResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON) // в каком формате вернет(или положит?) значение наш контроллер// ?
                        .content(accountDtoStrindData)) // кладем, что принимает контроллер
                .andReturn();                       // возвращает ответ из контроллера

        Assertions.assertEquals(201, accountCreatingResult.getResponse().getStatus());

        String accountResultJson = accountCreatingResult.getResponse().getContentAsString();
        Account accountResult = objectMapper.readValue(accountResultJson, Account.class);

        Assertions.assertEquals(accountDto.getName(), accountResult.getName());
        Assertions.assertEquals(accountDto.getType(), accountResult.getType().toString()); // зачем ту стринг?
        Assertions.assertEquals(accountDto.getStatus(), accountResult.getStatus().toString());
        Assertions.assertEquals(accountDto.getBalance(), accountResult.getBalance().toString());
        Assertions.assertEquals(accountDto.getCurrencyCode(), accountResult.getCurrencyCode().toString());
    }

    @Test
    @WithUserDetails(value = "alice@example.com")
    public void testGetByProductName() throws Exception {
    List<AccountDto> accountDtoList = new ArrayList<>();
//    AccountDto accountDto = new AccountDto();
//    accountDto.setProductName("Mortgage");
//    accountDto.setOwnerFullName();
//    accountDto.setName();
//    accountDto.setProductName();
//    accountDto.getId();
//
//    accountDtoList.add(accountDto);

    String getProductResult = mockMvc.perform(MockMvcRequestBuilders
                    .get("/account/get-by-productName")
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("productName", "Mortgage"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    List<AccountDto> actualListDto = objectMapper.readValue(getProductResult, new TypeReference<>() { //  new TypeReference - при приведении к коллекции
    });
    Assertions.assertEquals(accountDtoList, actualListDto);
    }
}