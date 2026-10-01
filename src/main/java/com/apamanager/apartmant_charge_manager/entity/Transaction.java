package com.apamanager.apartmant_charge_manager.entity;


import com.apamanager.apartmant_charge_manager.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transections")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer id;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_title")
    private String title;

    @Column(name = "transaction_amount",nullable = false,precision = 20 , scale = 5)
    private BigDecimal amount;

    @Column(name = "transaction_date_time")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private Building building;
}
