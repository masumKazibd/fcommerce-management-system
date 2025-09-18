package com.fcmis.fcmis_API.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class CreateSaleRequest {
    private long productId;
    private  Integer quantity;
    private LocalDate date;
    private BigDecimal sellPrice;

}
