package com.apamanager.apartmant_charge_manager.service;

import com.apamanager.apartmant_charge_manager.dto.response.ResponseChargeDto;

import java.util.List;

public interface ChargeService {

    public ResponseChargeDto findChargeById(Integer id);
    //  public ResponseChargeDto findChargeByTitle(String title);

    public List<ResponseChargeDto> findAll();

    public ResponseChargeDto addCharge(ResponseChargeDto responseChargeDto);

    public ResponseChargeDto updateChargeByTitle(String title, ResponseChargeDto responseChargeDto);

    public void deleteCharge();

}
