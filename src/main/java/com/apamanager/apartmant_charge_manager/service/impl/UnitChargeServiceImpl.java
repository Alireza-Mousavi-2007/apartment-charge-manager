package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestUnitChargeDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseUnitChargeDto;
import com.apamanager.apartmant_charge_manager.entity.UnitCharge;
import com.apamanager.apartmant_charge_manager.exceptions.UnitChargeNotFoundException;
import com.apamanager.apartmant_charge_manager.repository.UnitChargeRepository;
import com.apamanager.apartmant_charge_manager.service.UnitChargeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UnitChargeServiceImpl implements UnitChargeService {

    @Autowired
    private final UnitChargeRepository unitChargeRepository;

    public UnitChargeServiceImpl(UnitChargeRepository unitChargeRepository) {
        this.unitChargeRepository = unitChargeRepository;
    }

    @Override
    public ResponseUnitChargeDto findById(Integer id) {
        var unitCharge = unitChargeRepository.findById(id);
        if (unitCharge.isEmpty()) throw new UnitChargeNotFoundException("UnitCharge " + id + " doesn't exists");
        var unitChargeDto = ResponseUnitChargeDto.builder()
                .charge(unitCharge.get().getCharge())
                .unit(unitCharge.get().getUnit())
                .amountOwed(unitCharge.get().getAmountOwed())
                .isPaid(unitCharge.get().isPaid())
                .paidAt(unitCharge.get().getPaidAt())
                .build();

        return unitChargeDto;
    }

    @Override
    public List<ResponseUnitChargeDto> findAll() {
        var unitCharges = unitChargeRepository.findAll();

        var unitChargesDto = unitCharges.stream()
                .map(a -> ResponseUnitChargeDto.builder()
                        .charge(a.getCharge())
                        .unit(a.getUnit())
                        .amountOwed(a.getAmountOwed())
                        .isPaid(a.isPaid())
                        .paidAt(a.getPaidAt())
                        .build())
                .toList();
        return unitChargesDto;
    }

    @Override
    public ResponseUnitChargeDto addUnitCharge(RequestUnitChargeDto requestUnitChargeDto) {
        var unitCharge= UnitCharge.builder()
                .charge(requestUnitChargeDto.getCharge())
                .unit(requestUnitChargeDto.getUnit())
                .amountOwed(requestUnitChargeDto.getAmountOwed())
                .isPaid(requestUnitChargeDto.isPaid())
                .paidAt(requestUnitChargeDto.getPaidAt())
                .build();

        unitChargeRepository.save(unitCharge);


        var unitChargeDto = ResponseUnitChargeDto.builder()
                .charge(unitCharge.getCharge())
                .unit(unitCharge.getUnit())
                .amountOwed(unitCharge.getAmountOwed())
                .isPaid(unitCharge.isPaid())
                .paidAt(unitCharge.getPaidAt())
                .build();

        return unitChargeDto;
    }

    @Override
    public void deleteCharge(Integer id) {
        var unitCharge = unitChargeRepository.findById(id);
        if (unitCharge.isEmpty()) throw new UnitChargeNotFoundException("UnitCharge " + id + " doesn't exists");

        unitChargeRepository.delete(unitCharge.get());
    }
}
