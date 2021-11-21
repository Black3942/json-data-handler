package com.example.sciforcetest.controller;

import com.example.sciforcetest.dto.ProductDto;
import com.example.sciforcetest.service.response.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<ProductDto> get() {
        return productService.get();
    }

    @GetMapping("/all")
    public List<ProductDto> getAll() {
        return productService.getAll();
    }
}
