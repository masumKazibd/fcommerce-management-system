package com.fcmis.fcmis_API.DTOs;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.*;
import java.time.LocalDate;

@Getter @Setter
public class CreateSaleRequest {
    @NotNull private Long productId;
    @NotNull
    @Min(1) private Integer quantity;
    @NotNull private LocalDate date;
    private BigDecimal unitSellPrice;
}
