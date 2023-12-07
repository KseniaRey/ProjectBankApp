package com.example.bankapp.mapper;

import com.example.bankapp.dto.AgreementDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Agreement;
import com.example.bankapp.entity.Product;
import com.example.bankapp.entity.User;
import com.example.bankapp.enums.AgreementStatus;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-06T22:40:20+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class AgreementMapperImpl implements AgreementMapper {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void updateAgreementFromDto(AgreementDto agreementDto, Agreement agreement) {
        if ( agreementDto == null ) {
            return;
        }

        if ( agreementDto.getSum() != null ) {
            agreement.setSum( accountMapper.stringToBigDecimal( agreementDto.getSum() ) );
        }
        if ( agreementDto.getStatus() != null ) {
            agreement.setStatus( Enum.valueOf( AgreementStatus.class, agreementDto.getStatus() ) );
        }
    }

    @Override
    public AgreementDto mapToDto(Agreement existingAgreement) {
        if ( existingAgreement == null ) {
            return null;
        }

        AgreementDto agreementDto = new AgreementDto();

        UUID id = existingAgreementManagerId( existingAgreement );
        if ( id != null ) {
            agreementDto.setManagerId( id.toString() );
        }
        Integer id1 = existingAgreementProductId( existingAgreement );
        if ( id1 != null ) {
            agreementDto.setProductId( String.valueOf( id1 ) );
        }
        UUID id2 = existingAgreementAccountId( existingAgreement );
        if ( id2 != null ) {
            agreementDto.setAccountId( id2.toString() );
        }
        BigDecimal interestRate = existingAgreementProductInterestRate( existingAgreement );
        if ( interestRate != null ) {
            agreementDto.setInterestRate( interestRate.toString() );
        }
        Integer minLimit = existingAgreementProductMinLimit( existingAgreement );
        if ( minLimit != null ) {
            agreementDto.setSum( String.valueOf( minLimit ) );
        }
        if ( existingAgreement.getId() != null ) {
            agreementDto.setId( existingAgreement.getId().toString() );
        }
        if ( existingAgreement.getStatus() != null ) {
            agreementDto.setStatus( existingAgreement.getStatus().name() );
        }

        return agreementDto;
    }

    @Override
    public Agreement mapToEntity(AgreementDto agreementDto) {
        if ( agreementDto == null ) {
            return null;
        }

        Agreement agreement = new Agreement();

        if ( agreementDto.getId() != null ) {
            agreement.setId( UUID.fromString( agreementDto.getId() ) );
        }
        if ( agreementDto.getStatus() != null ) {
            agreement.setStatus( Enum.valueOf( AgreementStatus.class, agreementDto.getStatus() ) );
        }
        if ( agreementDto.getSum() != null ) {
            agreement.setSum( new BigDecimal( agreementDto.getSum() ) );
        }

        return agreement;
    }

    private UUID existingAgreementManagerId(Agreement agreement) {
        if ( agreement == null ) {
            return null;
        }
        User manager = agreement.getManager();
        if ( manager == null ) {
            return null;
        }
        UUID id = manager.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer existingAgreementProductId(Agreement agreement) {
        if ( agreement == null ) {
            return null;
        }
        Product product = agreement.getProduct();
        if ( product == null ) {
            return null;
        }
        Integer id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID existingAgreementAccountId(Agreement agreement) {
        if ( agreement == null ) {
            return null;
        }
        Account account = agreement.getAccount();
        if ( account == null ) {
            return null;
        }
        UUID id = account.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private BigDecimal existingAgreementProductInterestRate(Agreement agreement) {
        if ( agreement == null ) {
            return null;
        }
        Product product = agreement.getProduct();
        if ( product == null ) {
            return null;
        }
        BigDecimal interestRate = product.getInterestRate();
        if ( interestRate == null ) {
            return null;
        }
        return interestRate;
    }

    private Integer existingAgreementProductMinLimit(Agreement agreement) {
        if ( agreement == null ) {
            return null;
        }
        Product product = agreement.getProduct();
        if ( product == null ) {
            return null;
        }
        Integer minLimit = product.getMinLimit();
        if ( minLimit == null ) {
            return null;
        }
        return minLimit;
    }
}
