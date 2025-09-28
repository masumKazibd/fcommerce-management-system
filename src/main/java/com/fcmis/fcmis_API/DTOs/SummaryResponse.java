package com.fcmis.fcmis_API.DTOs;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @Builder
public class SummaryResponse {
    private BigDecimal grossRevenue;
    private BigDecimal totalCOGS;
    private BigDecimal totalExpenses;
    private BigDecimal netProfit;
    private Integer totalStockQty;
    private BigDecimal stockValue;
}

