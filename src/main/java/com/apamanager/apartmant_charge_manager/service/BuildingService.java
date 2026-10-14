package com.apamanager.apartmant_charge_manager.service;

import com.apamanager.apartmant_charge_manager.dto.request.RequestBuildingDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseBuildingDto;

import java.util.List;

public interface BuildingService {
    public ResponseBuildingDto findBuildingByName(String name);

    public ResponseBuildingDto findBuildingByAddress(String address);

    public List<ResponseBuildingDto> findAll();

    public ResponseBuildingDto addBuilding(RequestBuildingDto requestBuildingDto);

    public void deleteBuilding();

}
