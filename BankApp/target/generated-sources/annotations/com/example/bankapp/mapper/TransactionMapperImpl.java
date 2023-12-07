package com.example.bankapp.mapper;

import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.enums.TransactionType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-06T22:40:20+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDto toTransactionDto(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setDebitAccountId( uuidToString( transactionDebitAccountId( transaction ) ) );
        transactionDto.setCreditAccountId( uuidToString( transactionCreditAccountId( transaction ) ) );
        if ( transaction.getId() != null ) {
            transactionDto.setId( transaction.getId().toString() );
        }
        if ( transaction.getType() != null ) {
            transactionDto.setType( transaction.getType().name() );
        }
        if ( transaction.getAmount() != null ) {
            transactionDto.setAmount( transaction.getAmount().toString() );
        }
        transactionDto.setDescription( transaction.getDescription() );
        if ( transaction.getCreatedAt() != null ) {
            transactionDto.setCreatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( transaction.getCreatedAt() ) );
        }

        return transactionDto;
    }

    @Override
    public Transaction toTransactionEntity(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setAmount( stringToBigDecimal( transactionDto.getAmount() ) );
        if ( transactionDto.getType() != null ) {
            transaction.setType( Enum.valueOf( TransactionType.class, stringToEnumValue( transactionDto.getType() ) ) );
        }
        if ( transactionDto.getId() != null ) {
            transaction.setId( UUID.fromString( transactionDto.getId() ) );
        }
        transaction.setDescription( transactionDto.getDescription() );
        if ( transactionDto.getCreatedAt() != null ) {
            transaction.setCreatedAt( LocalDateTime.parse( transactionDto.getCreatedAt() ) );
        }

        return transaction;
    }

    @Override
    public List<TransactionDto> transactonsToTransactionsDTO(List<Transaction> transactions) {
        if ( transactions == null ) {
            return null;
        }

        List<TransactionDto> list = new ArrayList<TransactionDto>( transactions.size() );
        for ( Transaction transaction : transactions ) {
            list.add( toTransactionDto( transaction ) );
        }

        return list;
    }

    private UUID transactionDebitAccountId(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }
        Account debitAccount = transaction.getDebitAccount();
        if ( debitAccount == null ) {
            return null;
        }
        UUID id = debitAccount.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID transactionCreditAccountId(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }
        Account creditAccount = transaction.getCreditAccount();
        if ( creditAccount == null ) {
            return null;
        }
        UUID id = creditAccount.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
