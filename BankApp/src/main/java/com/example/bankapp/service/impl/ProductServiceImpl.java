package com.example.bankapp.service.impl;

import com.example.bankapp.entity.Product;
import com.example.bankapp.repository.ProductRepository;
import com.example.bankapp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional
    @Override
    public Product getById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity is not found"));
    }
}
