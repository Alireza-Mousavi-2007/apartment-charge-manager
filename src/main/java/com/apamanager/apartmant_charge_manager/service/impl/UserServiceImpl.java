package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestUserDto;
import com.apamanager.apartmant_charge_manager.dto.request.RequestUserUpdateDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseUserDto;
import com.apamanager.apartmant_charge_manager.entity.User;
import com.apamanager.apartmant_charge_manager.exceptions.UserNotFoundException;
import com.apamanager.apartmant_charge_manager.repository.AuthorityRepository;
import com.apamanager.apartmant_charge_manager.repository.RoleRepository;
import com.apamanager.apartmant_charge_manager.repository.UserRepository;
import com.apamanager.apartmant_charge_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {

    @Autowired
    private final AuthorityRepository authorityRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(AuthorityRepository authorityRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseUserDto findUser(String username) {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UserNotFoundException(username + " doesn't exists");

        var userDto = ResponseUserDto.builder()
                .username(user.get().getUsername())
                .roles(user.get().getRoles())
                .unit(user.get().getUnit())
                .building(user.get().getBuilding())
                .build();

        return userDto;
    }

    @Override
    public List<ResponseUserDto> findAll() {

        var users = userRepository.findAll();
        var userDtos = users.stream()
                .map(a -> ResponseUserDto.builder()
                        .username(a.getUsername())
                        .roles(a.getRoles())
                        .unit(a.getUnit())
                        .building(a.getBuilding())
                        .build()
                ).toList();

        return userDtos;
    }

    @Override
    public ResponseUserDto addUser(RequestUserDto requestUserDto) {
        var user = User.builder()
                .username(requestUserDto.getUsername())
                .password(requestUserDto.getPassword())
                .roles(requestUserDto.getRoles())
                .unit(requestUserDto.getUnit())
                .building(requestUserDto.getBuilding())
                .build();

        userRepository.save(user);

        var userDto = ResponseUserDto.builder()
                .username(user.getUsername())
                .unit(user.getUnit())
                .roles(user.getRoles())
                .building(user.getBuilding())
                .build();

        return userDto;
    }

    @Override
    public ResponseUserDto updateUserByAdmin(String username, RequestUserDto requestUserDto) {

        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UserNotFoundException(username + " doesn't exists");
        user.get().setUsername(requestUserDto.getUsername());
        user.get().setPassword(requestUserDto.getPassword());
        user.get().setRoles(requestUserDto.getRoles());
        user.get().setUnit(requestUserDto.getUnit());
        user.get().setBuilding(requestUserDto.getBuilding());

        userRepository.save(user.get());

        var userDto = ResponseUserDto.builder()
                .username(user.get().getUsername())
                .roles(user.get().getRoles())
                .unit(user.get().getUnit())
                .building(user.get().getBuilding())
                .build();

        return userDto;
    }

    @Override
    public ResponseUserDto updateUserByUser(String username, RequestUserUpdateDto requestUserUpdateDto) {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UserNotFoundException(username + " doesn't exists");

        user.get().setUsername(requestUserUpdateDto.getUsername());
        user.get().setPassword(requestUserUpdateDto.getPassword());

        userRepository.save(user.get());
        var userDto = ResponseUserDto.builder()
                .username(user.get().getUsername())
                .build();

        return userDto;
    }

    @Override
    public void deleteUser(String username) {

        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UserNotFoundException(username + " doesn't exists");

        userRepository.delete(user.get());

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UserNotFoundException(username + " doesn't exists");

        return user.get();
    }
}
