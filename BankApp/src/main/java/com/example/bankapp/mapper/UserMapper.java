package com.example.bankapp.mapper;

import com.example.bankapp.dto.UserDto;
import com.example.bankapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto mapToDTO(User resultUser);
}
