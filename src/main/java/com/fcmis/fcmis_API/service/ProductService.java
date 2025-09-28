package com.fcmis.fcmis_API.service;

import com.fcmis.fcmis_API.DTOs.ProductDTO;
import com.fcmis.fcmis_API.domain.Product;
import com.fcmis.fcmis_API.repo.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service @RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;

    @Transactional
    public Product create(ProductDTO dto) {
        Product p = Product.builder()
                .name(dto.getName().trim())
                .buyPrice(dto.getBuyPrice())
                .sellPrice(dto.getSellPrice())
                .stockQty(dto.getStockQty())
                .build();
        return productRepo.save(p);
    }

    public List<Product> list() { return productRepo.findAll(); }

    public Product get(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Transactional
    public Product update(Long id, ProductDTO dto) {
        Product p = get(id);
        p.setName(dto.getName().trim());
        p.setBuyPrice(dto.getBuyPrice());
        p.setSellPrice(dto.getSellPrice());
        p.setStockQty(dto.getStockQty());
        return productRepo.save(p);
    }
}