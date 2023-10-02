package com.example.bankapp.mapper;

import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Transaction;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface TransactionMapper {
    @Named("toTransactionDto")
    @Mapping(source = "debitAccount.id", target = "debitAccountId", qualifiedByName = "uuidToString")
    @Mapping(source = "creditAccount.id", target = "creditAccountId", qualifiedByName = "uuidToString")
    TransactionDto toTransactionDto(Transaction transaction); // to entity?
    @IterableMapping(qualifiedByName = "toTransactionDto") // к каждому элементу листа примени этот метод
    List<TransactionDto> transactonsToTransactionsDTO(List<Transaction> transactions); // list of dtos
    @Named("uuidToString")
    default String uuidToString (UUID uuid){
        return uuid.toString();
    }


}
