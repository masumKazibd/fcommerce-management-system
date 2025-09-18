package com.fcmis.fcmis_API.DTOs;

import com.fcmis.fcmis_API.model.Expense;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class CreateExpenseRequest {
    private Expense.Type type;
    private LocalDate date;
    private BigDecimal amount;
    private String note;

}
