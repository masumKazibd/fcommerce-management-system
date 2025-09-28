package com.fcmis.fcmis_API.service;

import com.fcmis.fcmis_API.DTOs.ProductDTO;
import com.fcmis.fcmis_API.model.Product;
import com.fcmis.fcmis_API.repo.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service @RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product create(ProductDTO dto) {
        Product p = Product.builder()
                .name(dto.getName().trim())
                .buyPrice(dto.getBuyPrice())
                .sellPrice(dto.getSellPrice())
                .quantity(dto.getQuantity())
                .build();
        return productRepository.save(p);
    }
    public List<Product> list() {return productRepository.findAll();}
    public  Product get(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Transactional
    public Product update(Long id, ProductDTO dto){
        Product p = get(id);
        p.setName(dto.getName().trim());
        p.setBuyPrice(dto.getBuyPrice());
        p.setSellPrice(dto.getSellPrice());
        p.setQuantity(dto.getQuantity());
        return productRepository.save(p);
    }

}