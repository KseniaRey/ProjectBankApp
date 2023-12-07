package com.example.bankapp.controller;

import com.example.bankapp.dto.UserDto;
import com.example.bankapp.security.Jwtprovider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/db/drop-tables.sql")
@Sql("/db/create-tables.sql")
@Sql("/db/insert-test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Jwtprovider jwtprovider;

    @Test
    void auth() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("john@example.com");
        userDto.setPassword("password123");

        String userDtoJson = objectMapper.writeValueAsString(userDto);
        String tokenResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertTrue(jwtprovider.validateToken(tokenResult));
        Assertions.assertEquals(userDto.getEmail(), jwtprovider.getEmailFromToken(tokenResult));
    }
}