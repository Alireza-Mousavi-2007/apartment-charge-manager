package com.apamanager.apartmant_charge_manager.service;

import com.apamanager.apartmant_charge_manager.dto.request.RequestChargeDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseChargeDto;

import java.util.List;

public interface ChargeService {

    public ResponseChargeDto findChargeById(Integer id);
    //  public ResponseChargeDto findChargeByTitle(String title);

    public List<ResponseChargeDto> findAll();

    public ResponseChargeDto addCharge(RequestChargeDto requestChargeDto);

    public ResponseChargeDto updateChargeById(Integer id, RequestChargeDto requestChargeDto);

    public void deleteChargeById(Integer id);

}
