package com.example.bankapp.controller;

import com.example.bankapp.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @WithUserDetails(value = "john@example.com")
    public void testDeleteUserById() throws Exception{
        String id = "5507c281-154a-4dd1-ba8e-6b32ff4676d0";
        UserDto userDto = new UserDto();
        userDto.setId(id);

        String userDtoJson = mockMvc.perform(MockMvcRequestBuilders.get("/user/" + id))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        UserDto resultUserDto = objectMapper.readValue(userDtoJson, UserDto.class);
        Assertions.assertEquals(userDto.getId(), resultUserDto.getId());

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/" + id))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + id))
                .andExpect(status().is(204));
    }
}
