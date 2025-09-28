package com.fcmis.fcmis_API.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal buyPrice;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal sellPrice;

    @Column(nullable=false)
    private Integer stockQty;
}