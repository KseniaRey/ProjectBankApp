package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ProductDto;
import com.example.bankapp.entity.Product;
import com.example.bankapp.mapper.ProductMapper;
import com.example.bankapp.repository.ProductRepository;
import com.example.bankapp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    @Transactional
    @Override
    public ProductDto getById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity is not found"));
        return productMapper.toProductDto(product);
    }
}
