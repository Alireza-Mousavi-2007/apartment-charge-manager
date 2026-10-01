package com.apamanager.apartmant_charge_manager.entity;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;

@Entity
@Table(name = "units",
        uniqueConstraints = @UniqueConstraint(
                name = "building_unit_unique",
                columnNames = {"building_id", "unit_number"}
        ))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Integer id;

    @Column(name = "unit_number", nullable = false)
    private String number;

    @Column(name = "household_size", nullable = false)
    private Integer householdSize;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "unit_building",
            joinColumns = @JoinColumn(name = "unit_id"),
            inverseJoinColumns = @JoinColumn(name = "building_id")
    )
    private Building building;

    //TODO: check this
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "unit_debt", nullable = false, precision = 20, scale = 5)
    private BigDecimal debt;
    //TODO: check if it should be a balance

}
