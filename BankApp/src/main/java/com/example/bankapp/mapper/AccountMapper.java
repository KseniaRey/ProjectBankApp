package com.example.bankapp.mapper;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "id", target = "id")
    AccountDto toAccountDto(Account account);

    List<AccountDto> accountsToAccountDto(List<Account> accounts);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "type", target = "type", qualifiedByName = "stringToEnumValue")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "balance", target = "balance", qualifiedByName = "stringToBigDecimal")
    @Mapping(source = "currencyCode", target = "currencyCode", qualifiedByName = "stringToEnumValue")
//    @Mapping(source = "id", target = "client", qualifiedByName = "stringToUuid") // с этим все ломается
    Account toAccountEntity(AccountDto accountDto);

//    @Named("stringToUuid")
//    default UUID stringToUuid(String clientId) {
//        return UUID.fromString(clientId);
//    }

    @Named("stringToEnumValue") // дублирование кода из transactionMapper - можем ли тут сделать через use чтобы использовать методы транзакшн маппера?
    default String stringToEnumValue(String s){
        return s.toUpperCase().replaceAll("\\s", "_");
    }

    @Named("stringToBigDecimal")
    default BigDecimal stringToBigDecimal(String s) {
        return new BigDecimal(s);
    }
}
