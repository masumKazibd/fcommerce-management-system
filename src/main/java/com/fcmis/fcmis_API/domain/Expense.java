package com.fcmis.fcmis_API.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Expense {

    public enum Type { ADVERTISING, DELIVERY, OTHER }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Type type;

    @Column(nullable=false)
    private LocalDate date;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal amount;

    private String note;
}