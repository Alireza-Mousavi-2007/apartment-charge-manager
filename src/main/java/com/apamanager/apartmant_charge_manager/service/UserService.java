package com.apamanager.apartmant_charge_manager.service;

import com.apamanager.apartmant_charge_manager.dto.request.RequestUserDto;
import com.apamanager.apartmant_charge_manager.dto.request.RequestUserUpdateDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseUserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public ResponseUserDto findUser(String username);

    public List<ResponseUserDto> findAll();

    public ResponseUserDto addUser(RequestUserDto requestUserDto);

    public ResponseUserDto updateUserByAdmin(String username, RequestUserDto requestUserDto);

    public ResponseUserDto updateUserByUser(String username, RequestUserUpdateDto requestUserUpdateDto);

    public void deleteUser(String username);

}
