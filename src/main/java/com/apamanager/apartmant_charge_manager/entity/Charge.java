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
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charge_id")
    private Integer id;

    @Column(name = "charge_title", nullable = false)
    private String title;

    @Column(name = "charge_amount", nullable = false, precision = 20, scale = 5)
    private BigDecimal amount;

    @Column(name = "charge_date_time")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private Building building;

}
