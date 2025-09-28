package com.fcmis.fcmis_API.service;
import com.fcmis.fcmis_API.DTOs.CreateSaleRequest;
import com.fcmis.fcmis_API.domain.Product;
import com.fcmis.fcmis_API.domain.Sale;
import com.fcmis.fcmis_API.repo.ProductRepository;
import com.fcmis.fcmis_API.repo.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service @RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepo;
    private final ProductRepository productRepo;

    @Transactional
    public Sale createSale(CreateSaleRequest req) {
        Product product = productRepo.findById(req.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (product.getStockQty() < req.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        BigDecimal unitSell = req.getUnitSellPrice() != null ? req.getUnitSellPrice() : product.getSellPrice();
        BigDecimal totalAmt = unitSell.multiply(BigDecimal.valueOf(req.getQuantity()));

        BigDecimal unitBuy = product.getBuyPrice();
        BigDecimal cogs = unitBuy.multiply(BigDecimal.valueOf(req.getQuantity()));

        // স্টক কমাও
        product.setStockQty(product.getStockQty() - req.getQuantity());
        productRepo.save(product);

        Sale sale = Sale.builder()
                .product(product)
                .date(req.getDate())
                .quantity(req.getQuantity())
                .unitSellPrice(unitSell)
                .totalAmount(totalAmt)
                .unitBuyPrice(unitBuy)
                .cogsAmount(cogs)
                .build();
        return saleRepo.save(sale);
    }
}
