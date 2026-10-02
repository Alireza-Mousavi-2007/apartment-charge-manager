package com.apamanager.apartmant_charge_manager.service;

import com.apamanager.apartmant_charge_manager.dto.request.RequestRoleDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseRoleDto;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    public ResponseRoleDto findRole(String Role);

    public List<ResponseRoleDto> findAll();

    public ResponseRoleDto addRole(RequestRoleDto requestRoleDto);

    public void deleteRole();
}
