package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestUserDto;
import com.apamanager.apartmant_charge_manager.dto.request.RequestUserUpdateDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseUserDto;
import com.apamanager.apartmant_charge_manager.entity.*;
import com.apamanager.apartmant_charge_manager.repository.RoleRepository;
import com.apamanager.apartmant_charge_manager.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;


    private Authority authority;
    private Role role;
    private Building building;
    private Unit unit;
    private User user;
    private RequestUserDto requestUserDto;
    private ResponseUserDto responseUserDto;
    private RequestUserUpdateDto requestUserUpdateDto;


    @BeforeEach
    public void init() {
        authority = Authority.builder().authority("authority").build();
        role = Role.builder().role("role").authorities(Set.of(authority)).build();
        building = Building.builder().name("name").address("address").build();
        unit = Unit.builder().number("number").householdSize(1).building(building).user(user).debt(BigDecimal.valueOf(1)).build();
        user = User.builder().id(1).username("username").password("password").roles(Set.of(role)).building(building).unit(unit).build();
        requestUserDto = RequestUserDto.builder().username("username").password("password").roles(Set.of(role)).building(building).unit(unit).build();
        responseUserDto = ResponseUserDto.builder().username("username").roles(Set.of(role)).building(building).unit(unit).build();
        requestUserUpdateDto =RequestUserUpdateDto.builder().username("username").password("password").build();

    }

    @Test
    public void findUser_found_responseUserDto() {

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(roleRepository.findByRole(anyString())).thenReturn(Optional.of(role));

        var test = userService.findUser(user.getUsername());

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(test.getRoles()).isEqualTo(user.getRoles());
        Assertions.assertThat(test.getUnit()).isEqualTo(user.getUnit());

    }

    @Test
    public void findAll_found_listOfResponseUserDto() {

        when(userRepository.findAll()).thenReturn(List.of(user));

        var test = userService.findAll();

        Assertions.assertThat(test).isNotEmpty();
        Assertions.assertThat(test.size()).isGreaterThan(0);
        Assertions.assertThat(test).extracting(a -> a.getUsername()).contains(user.getUsername());

    }

    @Test
    public void addUser_added_responseUserDto() {

        var test = userService.addUser(requestUserDto);

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(test.getRoles()).isEqualTo(user.getRoles());
        Assertions.assertThat(test.getUnit()).isEqualTo(user.getUnit());
    }

    @Test
    public void updateUserByAdmin_updated_responseUserDto() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        var test = userService.updateUserByAdmin(user.getUsername(), requestUserDto);

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(test.getRoles()).isEqualTo(user.getRoles());
        Assertions.assertThat(test.getUnit()).isEqualTo(user.getUnit());
    }

    @Test
    public void updateUserByUser_updated_responseUserDto() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        var test = userService.updateUserByUser(user.getUsername(),requestUserUpdateDto );

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getUsername()).isEqualTo(user.getUsername());

    }
    
    @Test
    public void deleteUser_deleted_void(){
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        userService.deleteUser(user.getUsername());

        verify(userRepository , times(1)).delete(any());
    }

}
