package com.example.bankapp.mapper;

import com.example.bankapp.dto.ProductDto;
import com.example.bankapp.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-06T22:40:19+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        if ( product.getId() != null ) {
            productDto.setId( String.valueOf( product.getId() ) );
        }
        productDto.setName( product.getName() );
        if ( product.getStatus() != null ) {
            productDto.setStatus( product.getStatus().name() );
        }
        if ( product.getCurrencyCode() != null ) {
            productDto.setCurrencyCode( product.getCurrencyCode().name() );
        }
        if ( product.getInterestRate() != null ) {
            productDto.setInterestRate( product.getInterestRate().toString() );
        }
        if ( product.getMinLimit() != null ) {
            productDto.setMinLimit( String.valueOf( product.getMinLimit() ) );
        }

        return productDto;
    }
}
