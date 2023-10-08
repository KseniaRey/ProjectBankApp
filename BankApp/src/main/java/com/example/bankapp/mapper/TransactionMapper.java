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
    TransactionDto toTransactionDto(Transaction transaction);


// При попытке внести вот это изменение, класс TransactionMapperImpl хочет, чтобы я оверрайднула новый метод. Если я это делаю,
    // появляется сообщение this class can not be changed. -> как решить?
//    @Named("toTransaction")
//    @Mapping(source = "debitAccountId", target = "debitAccount", qualifiedByName = "stringToUuid")
//    @Mapping(source = "creditAccountId", target = "creditAccount", qualifiedByName = "stringToUuid")
//    Transaction toTransaction(TransactionDto transactionDto);

    @IterableMapping(qualifiedByName = "toTransactionDto") // к каждому элементу листа примени этот метод
    List<TransactionDto> transactonsToTransactionsDTO(List<Transaction> transactions); // list of dtos
    @Named("uuidToString")
    default String uuidToString (UUID uuid){
        return uuid.toString();
    }


}
