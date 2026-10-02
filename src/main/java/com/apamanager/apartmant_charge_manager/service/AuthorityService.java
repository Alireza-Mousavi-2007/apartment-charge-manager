package com.apamanager.apartmant_charge_manager.service;

import com.apamanager.apartmant_charge_manager.dto.request.RequestAuthorityDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseAuthorityDto;

import java.util.List;


public interface AuthorityService {

    public ResponseAuthorityDto findAuthorityByName(String authorityName);

    public List<ResponseAuthorityDto> findAll();

    public ResponseAuthorityDto addAuthority(RequestAuthorityDto requestAuthorityDto);

    public void deleteAuthority();
}
