package com.fcmis.fcmis_API.service;

import com.fcmis.fcmis_API.DTOs.SummaryResponse;
import com.fcmis.fcmis_API.domain.*;
import com.fcmis.fcmis_API.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service @RequiredArgsConstructor
public class ReportService {
    private final SaleRepository saleRepo;
    private final ExpenseRepository expenseRepo;
    private final ProductRepository productRepo;

    public SummaryResponse summary(LocalDate from, LocalDate to) {
        List<Sale> sales = saleRepo.findByDateBetween(from, to);
        List<Expense> expenses = expenseRepo.findByDateBetween(from, to);
        List<Product> products = productRepo.findAll();

        BigDecimal revenue = sales.stream().map(Sale::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCogs = sales.stream().map(Sale::getCogsAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expTotal = expenses.stream().map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal net = revenue.subtract(totalCogs).subtract(expTotal);

        int totalStock = products.stream().mapToInt(Product::getStockQty).sum();

        BigDecimal stockValue = products.stream()
                .map(p -> p.getBuyPrice().multiply(BigDecimal.valueOf(p.getStockQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return SummaryResponse.builder()
                .grossRevenue(revenue)
                .totalCOGS(totalCogs)
                .totalExpenses(expTotal)
                .netProfit(net)
                .totalStockQty(totalStock)
                .stockValue(stockValue)
                .build();
    }
}
