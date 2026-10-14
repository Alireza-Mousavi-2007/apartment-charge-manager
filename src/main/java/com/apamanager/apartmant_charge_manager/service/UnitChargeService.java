package com.apamanager.apartmant_charge_manager.service;

import com.apamanager.apartmant_charge_manager.dto.request.RequestUnitChargeDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseUnitChargeDto;

import java.util.List;

public interface UnitChargeService {

    public ResponseUnitChargeDto findById(Integer id);

    public List<ResponseUnitChargeDto> findAll();

    public ResponseUnitChargeDto addUnitCharge(RequestUnitChargeDto requestUnitChargeDto);

    public void deleteCharge(Integer id);

}
