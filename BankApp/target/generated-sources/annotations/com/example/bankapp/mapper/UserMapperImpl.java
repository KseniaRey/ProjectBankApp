package com.example.bankapp.mapper;

import com.example.bankapp.dto.UserDto;
import com.example.bankapp.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-06T22:40:20+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto mapToDTO(User resultUser) {
        if ( resultUser == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setEmail( resultUser.getEmail() );
        if ( resultUser.getId() != null ) {
            userDto.setId( resultUser.getId().toString() );
        }
        if ( resultUser.getStatus() != null ) {
            userDto.setStatus( resultUser.getStatus().name() );
        }
        userDto.setTaxCode( resultUser.getTaxCode() );
        userDto.setFirstName( resultUser.getFirstName() );
        userDto.setLastName( resultUser.getLastName() );
        userDto.setAddress( resultUser.getAddress() );
        userDto.setPhone( resultUser.getPhone() );
        if ( resultUser.getRole() != null ) {
            userDto.setRole( resultUser.getRole().name() );
        }

        return userDto;
    }
}
