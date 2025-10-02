package com.fcmis.fcmis_API.controller;
import com.fcmis.fcmis_API.DTOs.CreateSaleRequest;
import com.fcmis.fcmis_API.domain.Sale;
import com.fcmis.fcmis_API.repo.SaleRepository;
import com.fcmis.fcmis_API.service.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/sales") @RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;
    private final SaleRepository saleRepo;
    @PostMapping
    public Sale create(@Valid @RequestBody CreateSaleRequest req) {
        return saleService.createSale(req);
    }
    @GetMapping
    public List<Sale> getAllSales() {
        return saleRepo.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }
}