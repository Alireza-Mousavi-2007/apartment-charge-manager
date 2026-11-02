package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestBuildingDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseBuildingDto;
import com.apamanager.apartmant_charge_manager.entity.Building;
import com.apamanager.apartmant_charge_manager.exceptions.BuildingNotFoundException;
import com.apamanager.apartmant_charge_manager.repository.BuildingRepository;
import com.apamanager.apartmant_charge_manager.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public ResponseBuildingDto findBuildingByName(String name) {
        var building = buildingRepository.findBuildingByName(name);
        if (building.isEmpty()) throw new BuildingNotFoundException(name + " doesn't exists.");
        var buildingDto = ResponseBuildingDto.builder()
                .name(building.get().getName())
                .address(building.get().getAddress())
                .units(building.get().getUnits())
                .build();
        return buildingDto;
    }

    @Override
    public ResponseBuildingDto findBuildingByAddress(String address) {
        var building = buildingRepository.findBuildingByAddress(address);
        if (building.isEmpty()) throw new BuildingNotFoundException(address + " doesn't exists.");
        var buildingDto = ResponseBuildingDto.builder()
                .name(building.get().getName())
                .address(building.get().getAddress())
                .units(building.get().getUnits())
                .build();
        return buildingDto;
    }

    @Override
    public List<ResponseBuildingDto> findAll() {
        var buildings = buildingRepository.findAll();
        var buildingsDto = buildings.stream()
                .map(a -> ResponseBuildingDto.builder()
                        .name(a.getName())
                        .address(a.getAddress())
                        .units(a.getUnits())
                        .build())
                .toList();

        return buildingsDto;
    }

    @Override
    public ResponseBuildingDto addBuilding(RequestBuildingDto requestBuildingDto) {
        var building = Building.builder()
                .name(requestBuildingDto.getName())
                .address(requestBuildingDto.getAddress())
                .units(requestBuildingDto.getUnits())
                .build();
        buildingRepository.save(building);

        var buildingDto = ResponseBuildingDto.builder()
                .name(building.getName())
                .address(building.getAddress())
                .units(building.getUnits())
                .build();
        return buildingDto;

    }

    @Override
    public void deleteBuilding(String name) {
        var building = buildingRepository.findBuildingByName(name);
        if (building.isEmpty()) throw new BuildingNotFoundException(name + " doesn't exists.");
        buildingRepository.delete(building.get());
    }
}// TODO: add update building method
