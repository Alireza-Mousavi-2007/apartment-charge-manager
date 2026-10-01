package com.apamanager.apartmant_charge_manager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;


import java.util.Set;

@Entity
@Table(name = "buildings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private Integer id;

    @Column(name = "building_name", nullable = false)
    private String name;

    @Column(name = "building_address", nullable = false, columnDefinition = "TEXT")
    private String address;

    //TODO: check . mappedby
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Unit> units;



}
