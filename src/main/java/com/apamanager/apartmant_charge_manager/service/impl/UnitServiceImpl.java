package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestUnitDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseUnitDto;
import com.apamanager.apartmant_charge_manager.entity.Unit;
import com.apamanager.apartmant_charge_manager.exceptions.UnitNotFoundException;
import com.apamanager.apartmant_charge_manager.repository.UnitRepository;
import com.apamanager.apartmant_charge_manager.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }


    @Override
    public ResponseUnitDto findUnitByNumber(String number) {
        var unit = unitRepository.findUnitByNumber(number);
        if (unit.isEmpty()) throw new UnitNotFoundException("unit : " + number + " doesn't exists.");

        var responseDto = ResponseUnitDto.builder()
                .number(unit.get().getNumber())
                .householdSize(unit.get().getHouseholdSize())
                .building(unit.get().getBuilding())
                .user(unit.get().getUser())
                .debt(unit.get().getDebt())
                .build();


        return responseDto;
    }

    @Override
    public List<ResponseUnitDto> findAll() {
        var units = unitRepository.findAll();
        var responseDtos = units.stream()
                .map(a -> ResponseUnitDto.builder()
                        .number(a.getNumber())
                        .householdSize(a.getHouseholdSize())
                        .building(a.getBuilding())
                        .user(a.getUser())
                        .debt(a.getDebt())
                        .build())
                .toList();
        return responseDtos;
    }

    @Override
    public ResponseUnitDto addUnit(RequestUnitDto requestUnitDto) {
        var unit = Unit.builder()
                .number(requestUnitDto.getNumber())
                .householdSize(requestUnitDto.getHouseholdSize())
                .building(requestUnitDto.getBuilding())
                .user(requestUnitDto.getUser())
                .debt(requestUnitDto.getDebt())
                .build();

        unitRepository.save(unit);

        var responseDto = ResponseUnitDto.builder()
                .number(unit.getNumber())
                .householdSize(unit.getHouseholdSize())
                .building(unit.getBuilding())
                .user(unit.getUser())
                .debt(unit.getDebt())
                .build();

        return responseDto;
    }

    @Override
    public void deleteUnit(String number) {
        var unit = unitRepository.findUnitByNumber(number);
        if (unit.isEmpty()) throw new UnitNotFoundException("unit : " + number + " doesn't exists.");
        unitRepository.delete(unit.get());
    }

    @Override
    public ResponseUnitDto updateUnitByNumber(String number, RequestUnitDto requestUnitDto) {
        var unit = unitRepository.findUnitByNumber(number);
        if (unit.isEmpty()) throw new UnitNotFoundException("unit : " + number + " doesn't exists.");

        unit.get().setNumber(requestUnitDto.getNumber());
        unit.get().setHouseholdSize(requestUnitDto.getHouseholdSize());
        unit.get().setBuilding(requestUnitDto.getBuilding());
        unit.get().setDebt(requestUnitDto.getDebt());
        unit.get().setUser(requestUnitDto.getUser());

        unitRepository.save(unit.get());

        var responseDto = ResponseUnitDto.builder()
                .number(unit.get().getNumber())
                .householdSize(unit.get().getHouseholdSize())
                .building(unit.get().getBuilding())
                .user(unit.get().getUser())
                .debt(unit.get().getDebt())
                .build();

        return responseDto;
    }
}
