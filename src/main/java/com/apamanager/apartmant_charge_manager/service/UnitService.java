package com.apamanager.apartmant_charge_manager.service;

import com.apamanager.apartmant_charge_manager.dto.request.RequestUnitDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseUnitDto;

import java.util.List;

public interface UnitService {

    public ResponseUnitDto findUnitByNumber(String number);

    public List<ResponseUnitDto> findAll();

    public ResponseUnitDto addUnit(RequestUnitDto requestUnitDto);

    public void deleteUnit(String number);

    public RequestUnitDto updateUnitByNumber(String number, RequestUnitDto requestUnitDto);
}
