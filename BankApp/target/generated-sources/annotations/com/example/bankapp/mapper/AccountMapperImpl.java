package com.example.bankapp.mapper;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.User;
import com.example.bankapp.enums.AccountType;
import com.example.bankapp.enums.Currency;
import com.example.bankapp.enums.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-25T18:32:35+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDto toAccountDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        UUID id = accountClientId( account );
        if ( id != null ) {
            accountDto.setClientId( id.toString() );
        }
        if ( account.getId() != null ) {
            accountDto.setId( account.getId().toString() );
        }
        accountDto.setName( account.getName() );
        if ( account.getType() != null ) {
            accountDto.setType( account.getType().name() );
        }
        if ( account.getStatus() != null ) {
            accountDto.setStatus( account.getStatus().name() );
        }
        if ( account.getBalance() != null ) {
            accountDto.setBalance( account.getBalance().toString() );
        }
        if ( account.getCurrencyCode() != null ) {
            accountDto.setCurrencyCode( account.getCurrencyCode().name() );
        }

        return accountDto;
    }

    @Override
    public List<AccountDto> accountsToAccountDto(List<Account> accounts) {
        if ( accounts == null ) {
            return null;
        }

        List<AccountDto> list = new ArrayList<AccountDto>( accounts.size() );
        for ( Account account : accounts ) {
            list.add( toAccountDto( account ) );
        }

        return list;
    }

    @Override
    public Account toAccountEntity(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setName( accountDto.getName() );
        if ( accountDto.getType() != null ) {
            account.setType( Enum.valueOf( AccountType.class, stringToEnumValue( accountDto.getType() ) ) );
        }
        if ( accountDto.getStatus() != null ) {
            account.setStatus( Enum.valueOf( Status.class, accountDto.getStatus() ) );
        }
        account.setBalance( stringToBigDecimal( accountDto.getBalance() ) );
        if ( accountDto.getCurrencyCode() != null ) {
            account.setCurrencyCode( Enum.valueOf( Currency.class, stringToEnumValue( accountDto.getCurrencyCode() ) ) );
        }
        if ( accountDto.getId() != null ) {
            account.setId( UUID.fromString( accountDto.getId() ) );
        }

        return account;
    }

    private UUID accountClientId(Account account) {
        if ( account == null ) {
            return null;
        }
        User client = account.getClient();
        if ( client == null ) {
            return null;
        }
        UUID id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
