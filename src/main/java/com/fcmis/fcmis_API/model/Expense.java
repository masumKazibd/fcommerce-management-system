package com.fcmis.fcmis_API.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    public enum Type{Advertising, Delivery, OTHER};
    private long id;
    private Type type;
    private LocalDate date;
    private BigDecimal amount;
    private String note;
}
