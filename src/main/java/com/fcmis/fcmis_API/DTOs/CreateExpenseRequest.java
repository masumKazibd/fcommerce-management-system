package com.fcmis.fcmis_API.DTOs;

import com.fcmis.fcmis_API.domain.Expense;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class CreateExpenseRequest {
    @NotNull private Expense.Type type;
    @NotNull private LocalDate date;
    @NotNull @DecimalMin("0.00") private BigDecimal amount;
    private String note;
}
