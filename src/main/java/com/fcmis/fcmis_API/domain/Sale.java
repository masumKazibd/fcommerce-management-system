package com.fcmis.fcmis_API.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Sale {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JsonIgnore
    private Product product;

    @Column(nullable=false)
    private LocalDate date;

    @Column(nullable=false)
    private Integer quantity;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal unitSellPrice;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal totalAmount;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal unitBuyPrice;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal cogsAmount;

    @JsonProperty("productId")
    @Transient
    public Long getProductId() {
        return this.product != null ? this.product.getId() : null;
    }

}