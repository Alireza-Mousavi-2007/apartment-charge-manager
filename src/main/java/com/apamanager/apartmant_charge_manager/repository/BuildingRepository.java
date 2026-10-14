package com.apamanager.apartmant_charge_manager.repository;

import com.apamanager.apartmant_charge_manager.dto.response.ResponseBuildingDto;
import com.apamanager.apartmant_charge_manager.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Integer> {
    public Optional<Building> findBuildingByName(String name);

    public Optional<Building> findBuildingByAddress(String address);
}
