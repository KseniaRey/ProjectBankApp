package com.example.bankapp.mapper;

import com.example.bankapp.dto.ProductDto;
import com.example.bankapp.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toProductDto(Product product);
}
