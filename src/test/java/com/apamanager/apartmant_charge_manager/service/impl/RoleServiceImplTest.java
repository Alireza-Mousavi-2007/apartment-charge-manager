package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestRoleDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseRoleDto;
import com.apamanager.apartmant_charge_manager.entity.Authority;
import com.apamanager.apartmant_charge_manager.entity.Role;
import com.apamanager.apartmant_charge_manager.repository.AuthorityRepository;
import com.apamanager.apartmant_charge_manager.repository.RoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    @Mock
    private AuthorityRepository authorityRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private RoleServiceImpl roleService;


    private Authority authority;
    private Role role;
    private RequestRoleDto requestRoleDto;
    private ResponseRoleDto responseRoleDto;

    @BeforeEach
    public void init() {
        authority = Authority.builder().id(1).authority("authority").build();
        role = Role.builder().role("role").authorities(Set.of(authority)).build();
        responseRoleDto = ResponseRoleDto.builder().role("role").authorities(Set.of(authority)).build();
        requestRoleDto = RequestRoleDto.builder().role("role").authorities(Set.of(authority)).build();
    }

    @Test
    public void findRole_found_responseRoleDto() {
        when(roleRepository.findByRole(anyString())).thenReturn(Optional.of(role));
        when(authorityRepository.findByAuthority(anyString())).thenReturn(Optional.of(authority));

        var test = roleService.findRole(role.getRole());

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getRole()).isEqualTo(role.getRole());
        Assertions.assertThat(test.getAuthorities()).isEqualTo(role.getAuthorities());
    }

    @Test
    public void findAll_found_listOfResponseRoleDto() {
        when(roleRepository.findAll()).thenReturn(List.of(role));

        var test = roleService.findAll();

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.size()).isGreaterThan(0);
        Assertions.assertThat(test).extracting(a -> a.getRole()).contains(responseRoleDto.getRole());

    }

    @Test
    public void addRole_added_responseRoleDto(){
        when(authorityRepository.findByAuthority(anyString())).thenReturn(Optional.of(authority));

        var test = roleService.addRole(requestRoleDto);

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getRole()).isEqualTo(requestRoleDto.getRole());
        Assertions.assertThat(test.getAuthorities()).isEqualTo(requestRoleDto.getAuthorities());
    }

    @Test
    public void deleteRole_deleted_void(){
        when(roleRepository.findByRole(anyString())).thenReturn(Optional.of(role));

        roleService.deleteRole(role.getRole());

        verify(roleRepository).delete(any());
    }


}
