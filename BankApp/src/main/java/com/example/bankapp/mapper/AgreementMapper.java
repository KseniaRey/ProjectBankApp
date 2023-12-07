package com.example.bankapp.mapper;

import com.example.bankapp.dto.AgreementDto;
import com.example.bankapp.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface AgreementMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sum", source = "sum", qualifiedByName = "stringToBigDecimal", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // ссылка из аккаунт маппера
    @Mapping(target = "status", source = "status", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAgreementFromDto(AgreementDto agreementDto, @MappingTarget Agreement agreement);

    @Mapping(target = "managerId", source = "manager.id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "interestRate", source = "product.interestRate")
    @Mapping(target = "sum", source = "product.minLimit")
    AgreementDto mapToDto(Agreement existingAgreement);

    Agreement mapToEntity(AgreementDto agreementDto);
}
