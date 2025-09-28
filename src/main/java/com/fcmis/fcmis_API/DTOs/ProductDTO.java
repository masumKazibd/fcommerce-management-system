package com.fcmis.fcmis_API.DTOs;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.*;

@Getter @Setter
public class ProductDTO {
    @NotBlank private String name;
    @NotNull @DecimalMin("0.00") private BigDecimal buyPrice;
    @NotNull @DecimalMin("0.00") private BigDecimal sellPrice;
    @NotNull @Min(0) private Integer stockQty;
}
