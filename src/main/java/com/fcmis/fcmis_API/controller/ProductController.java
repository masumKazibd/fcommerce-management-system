package com.fcmis.fcmis_API.controller;

import com.fcmis.fcmis_API.DTOs.ProductDTO;
import com.fcmis.fcmis_API.domain.Product;
import com.fcmis.fcmis_API.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/products") @RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public Product create(@Valid @RequestBody ProductDTO dto) { return productService.create(dto); }

    @GetMapping
    public List<Product> list() { return productService.list(); }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) { return productService.get(id); }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        return productService.update(id, dto);
    }
}