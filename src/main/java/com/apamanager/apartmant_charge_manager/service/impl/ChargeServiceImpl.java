package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestChargeDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseChargeDto;
import com.apamanager.apartmant_charge_manager.entity.Charge;
import com.apamanager.apartmant_charge_manager.exceptions.ChargeNotFoundException;
import com.apamanager.apartmant_charge_manager.repository.ChargeRepository;
import com.apamanager.apartmant_charge_manager.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChargeServiceImpl implements ChargeService {
    @Autowired
    private final ChargeRepository chargeRepository;

    public ChargeServiceImpl(ChargeRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
    }


    @Override
    public ResponseChargeDto findChargeById(Integer id) {

        var charge = chargeRepository.findChargeById(id);
        if (charge.isEmpty()) throw new ChargeNotFoundException("charge " + id + " doesn't exists");
        var chargeDto = ResponseChargeDto.builder()
                .title(charge.get().getTitle())
                .amount(charge.get().getAmount())
                .building(charge.get().getBuilding())
                .chargeType(charge.get().getChargeType())
                .dateTime(charge.get().getDateTime())
                .build();

        return chargeDto;
    }

    @Override
    public List<ResponseChargeDto> findAll() {

        var charges = chargeRepository.findAll();

        var chargeDtos = charges.stream()
                .map(a -> ResponseChargeDto.builder()
                        .title(a.getTitle())
                        .amount(a.getAmount())
                        .building(a.getBuilding())
                        .chargeType(a.getChargeType())
                        .dateTime(a.getDateTime())
                        .build())
                .toList();
        return chargeDtos;
    }

    @Override
    public ResponseChargeDto addCharge(RequestChargeDto requestChargeDto) {
        var charge = Charge.builder()
                .title(requestChargeDto.getTitle())
                .amount(requestChargeDto.getAmount())
                .building(requestChargeDto.getBuilding())
                .chargeType(requestChargeDto.getChargeType())
                .dateTime(requestChargeDto.getDateTime())
                .build();

        chargeRepository.save(charge);

        var chargeDto = ResponseChargeDto.builder()
                .title(charge.getTitle())
                .amount(charge.getAmount())
                .building(charge.getBuilding())
                .chargeType(charge.getChargeType())
                .dateTime(charge.getDateTime())
                .build();

        return chargeDto;
    }

    @Override
    public ResponseChargeDto updateChargeById(Integer id, RequestChargeDto requestChargeDto) {

        var charge = chargeRepository.findChargeById(id);
        if (charge.isEmpty()) throw new ChargeNotFoundException("charge " + id + " doesn't exists");

        var savedCharge = Charge.builder()
                .title(requestChargeDto.getTitle())
                .amount(requestChargeDto.getAmount())
                .building(requestChargeDto.getBuilding())
                .chargeType(requestChargeDto.getChargeType())
                .dateTime(requestChargeDto.getDateTime())
                .build();

        chargeRepository.save(savedCharge);

        var chargeDto = ResponseChargeDto.builder()
                .title(savedCharge.getTitle())
                .amount(savedCharge.getAmount())
                .building(savedCharge.getBuilding())
                .chargeType(savedCharge.getChargeType())
                .dateTime(savedCharge.getDateTime())
                .build();

        return chargeDto;
    }

    @Override
    public void deleteChargeById(Integer id) {
        var charge = chargeRepository.findChargeById(id);
        if (charge.isEmpty()) throw new ChargeNotFoundException("charge " + id + " doesn't exists");
        chargeRepository.delete(charge.get());
    }
}
