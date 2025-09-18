package com.fcmis.fcmis_API.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {
    @NotNull private String name;
    @DecimalMin("0.00") private BigDecimal buyPrice;
    @DecimalMin("0.00") private BigDecimal sellPrice;
    @Min(0) private Integer quantity;
}
