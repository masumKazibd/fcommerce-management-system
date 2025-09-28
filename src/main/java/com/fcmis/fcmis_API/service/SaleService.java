package com.fcmis.fcmis_API.service;

import com.fcmis.fcmis_API.DTOs.CreateSaleRequest;
import com.fcmis.fcmis_API.model.Product;
import com.fcmis.fcmis_API.model.Sale;
import com.fcmis.fcmis_API.repo.ProductRepository;
import com.fcmis.fcmis_API.repo.SaleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service @RequiredArgsConstructor
public class SaleService {
    private  final SaleRepository saleRepository;
    private  final ProductRepository productRepository;

    @Transactional
    public Sale createSale(CreateSaleRequest req){
        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if(product.getStockQuantity() < req.getQuantity()){
            throw new IllegalArgumentException("Insufficient stock quantity");
        }

        BigDecimal unitSell = req.getSellPrice()!=null?req.getSellPrice():product.getSellPrice();
        BigDecimal totalAmount = unitSell.multiply(BigDecimal.valueOf(req.getQuantity()));

        BigDecimal unitBuy = product.getBuyPrice();
        BigDecimal cogs = unitBuy.multiply(BigDecimal.valueOf(req.getQuantity()));

        product.setStockQuantity(product.getStockQuantity() - req.getQuantity());
        productRepository.save(product);

        Sale sale = Sale.builder()
                .product(product)
                .date(req.getDate())
                .quantity(req.getQuantity())
                .unitSellPrice(unitSell)
                .totalAmount(totalAmount)
                .cogAmount(cogs)
                .build();
        return saleRepository.save(sale);

    }
}
