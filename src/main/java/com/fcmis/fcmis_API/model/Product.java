package com.fcmis.fcmis_API.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private  long id;
    private  String name;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private Integer quantity;

}
