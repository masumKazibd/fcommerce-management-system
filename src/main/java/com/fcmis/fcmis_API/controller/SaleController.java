package com.fcmis.fcmis_API.controller;
import com.fcmis.fcmis_API.DTOs.CreateSaleRequest;
import com.fcmis.fcmis_API.domain.Sale;
import com.fcmis.fcmis_API.service.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/sales") @RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public Sale create(@Valid @RequestBody CreateSaleRequest req) {
        return saleService.createSale(req);
    }
}