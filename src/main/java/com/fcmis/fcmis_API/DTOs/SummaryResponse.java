package com.fcmis.fcmis_API.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class SummaryResponse {
    private BigDecimal grosRevenue;
    private BigDecimal totalCOGs;
    private BigDecimal totalExpenses;
    private BigDecimal netProfit;
    private Integer totalStocksQuantity;
    private Integer totalStocksValue;


}
