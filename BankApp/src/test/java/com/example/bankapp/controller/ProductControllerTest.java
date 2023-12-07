package com.example.bankapp.controller;

import com.example.bankapp.dto.ProductDto;
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

@Sql("/db/drop-tables.sql")
@Sql("/db/create-tables.sql")
@Sql("/db/insert-test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithUserDetails(value = "alice@example.com")
    public void testGetProductById()throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId("1");
        productDto.setName("Mortgage");
        productDto.setStatus("ACTIVE");
        productDto.setCurrencyCode("USD");
        productDto.setInterestRate("3.5000");
        productDto.setMinLimit("100000");

        String getProduct = objectMapper.writeValueAsString(productDto);

        MvcResult mockResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/product/" + productDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                        .content(getProduct))
                .andReturn();

        ProductDto actual = objectMapper.readValue(mockResult.getResponse().getContentAsString(), ProductDto.class);

        Assertions.assertEquals(200, mockResult.getResponse().getStatus());
        Assertions.assertEquals(actual, productDto);


    }
}