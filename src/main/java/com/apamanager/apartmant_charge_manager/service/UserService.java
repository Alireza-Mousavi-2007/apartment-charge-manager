package com.apamanager.apartmant_charge_manager.service;

import com.apamanager.apartmant_charge_manager.dto.request.RequestUserDto;
import com.apamanager.apartmant_charge_manager.dto.request.RequestUserUpdateDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseUserDto;

import java.util.List;

public interface UserService {

    public ResponseUserDto findUser(String username);

    public List<ResponseUserDto> findAll();

    public ResponseUserDto addUser(RequestUserDto requestUserDto);

    public ResponseUserDto updateUserByAdmin(RequestUserDto requestUserDto);

    public ResponseUserDto updateUserByUser(RequestUserUpdateDto requestUserUpdateDto);

    public void deleteUser(String username);

}
