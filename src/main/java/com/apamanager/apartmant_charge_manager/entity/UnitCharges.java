package com.apamanager.apartmant_charge_manager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "charges")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UnitCharges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_charge_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charge_id")
    private Charge charge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "amount_owed",nullable = false,precision = 20,scale = 5)
    private BigDecimal amountOwed;

    @Column(name = "is_paid",nullable = false)
    private boolean isPaid;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

}
