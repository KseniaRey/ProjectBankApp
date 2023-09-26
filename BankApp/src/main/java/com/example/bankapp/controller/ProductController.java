package com.example.bankapp.controller;

import com.example.bankapp.entity.Product;
import com.example.bankapp.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // обработает HTTP-запросы, возвращая результаты в формате JSON.
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    private Product getProductById(@PathVariable(name = "id") int id){
    return productService.getById(id);
    }
}
