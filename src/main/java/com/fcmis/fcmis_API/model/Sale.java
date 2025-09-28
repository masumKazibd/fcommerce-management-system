package com.fcmis.fcmis_API.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sale {
    private long id;
    private  Product product;
    private LocalDate date;
    private  Integer quantity;
    private BigDecimal unitSellPrice;
    private BigDecimal totalAmount;
    private BigDecimal unitBuyPrice;
    private BigDecimal cogAmount;
    

}
