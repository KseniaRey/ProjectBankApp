package com.example.bankapp.mapper;

import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Transaction;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface TransactionMapper {
    @Named("toTransactionDto")
    @Mapping(source = "debitAccount.id", target = "debitAccountId", qualifiedByName = "uuidToString")
    @Mapping(source = "creditAccount.id", target = "creditAccountId", qualifiedByName = "uuidToString")
    TransactionDto toTransactionDto(Transaction transaction);

    @Mapping(source = "amount", target = "amount", qualifiedByName = "stringToBigDecimal")
    @Mapping(source = "type", target = "type", qualifiedByName = "stringToEnumValue")
    Transaction toTransactionEntity(TransactionDto transactionDto);

    @IterableMapping(qualifiedByName = "toTransactionDto")
    List<TransactionDto> transactonsToTransactionsDTO(List<Transaction> transactions);

    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid.toString();
    }

    @Named("stringToBigDecimal")
    default BigDecimal stringToBigDecimal(String s) {
        return new BigDecimal(s);
    }

    @Named("stringToEnumValue")
    default String stringToEnumValue(String s) {
        return s.toUpperCase().replaceAll("\\s", "_");
    }
}
