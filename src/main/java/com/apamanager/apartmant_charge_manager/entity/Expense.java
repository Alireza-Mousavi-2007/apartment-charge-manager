package com.apamanager.apartmant_charge_manager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Integer id;

    @Column(name = "expense_title", nullable = false)
    private String title;

    @Column(name = "expense_amount", nullable = false, precision = 20, scale = 5)
    private BigDecimal amount;

    @Column(name = "expense_date_time")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private Building building;

}
