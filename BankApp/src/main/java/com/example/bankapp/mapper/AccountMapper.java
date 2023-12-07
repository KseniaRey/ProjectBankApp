package com.example.bankapp.mapper;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Named("toAccountDto")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "agreement.product.name", target = "productName")
    @Mapping(source = "client", target = "ownerFullName", qualifiedByName = "getFullName")
    AccountDto toAccountDto(Account account);

    @IterableMapping(qualifiedByName = "toAccountDto")
    List<AccountDto> accountsToAccountDto(List<Account> accounts);
    @Named("toAccountEntity")
    @Mapping(source = "type", target = "type", qualifiedByName = "stringToEnumValue")
    @Mapping(source = "status", target = "status", qualifiedByName = "stringToEnumValue")
    @Mapping(source = "balance", target = "balance", qualifiedByName = "stringToBigDecimal")
    @Mapping(source = "currencyCode", target = "currencyCode", qualifiedByName = "stringToEnumValue")
    Account toAccountEntity(AccountDto accountDto);

    @Named("stringToEnumValue")
    default String stringToEnumValue(String s){
        return s.toUpperCase().replaceAll("\\s", "_");
    }

    @Named("stringToBigDecimal")
    default BigDecimal stringToBigDecimal(String s) {
        return new BigDecimal(s);
    }
    
    @Named("getFullName")
    default String getFullName(User user){
        return user.getFirstName() + " " + user.getLastName();
    }
}
