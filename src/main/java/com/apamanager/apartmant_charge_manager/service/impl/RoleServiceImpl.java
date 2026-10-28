package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestRoleDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseRoleDto;
import com.apamanager.apartmant_charge_manager.entity.Role;
import com.apamanager.apartmant_charge_manager.exceptions.AuthorityNotFoundException;
import com.apamanager.apartmant_charge_manager.exceptions.RoleNotFoundException;
import com.apamanager.apartmant_charge_manager.repository.AuthorityRepository;
import com.apamanager.apartmant_charge_manager.repository.RoleRepository;
import com.apamanager.apartmant_charge_manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final AuthorityRepository authorityRepository;

    public RoleServiceImpl(RoleRepository roleRepository, AuthorityRepository authorityRepository) {
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public ResponseRoleDto findRole(String roleName) {
        var role = roleRepository.findByRole(roleName);
        if (role.isEmpty()) throw new RoleNotFoundException(roleName + " doesn't exists");

        var foundRole = role.get();

        var authorities = foundRole.getAuthorities().stream()
                .map(a -> authorityRepository.findByAuthority(a.getAuthority()).get())
                .collect(Collectors.toSet());

        var responseRoleDto = ResponseRoleDto.builder()
                .Role(foundRole.getRole())
                .authorities(authorities)
                .build();


        return responseRoleDto;
    }

    @Override
    public List<ResponseRoleDto> findAll() {
        var roles = roleRepository.findAll();

        var rolesDto = roles.stream()
                .map(a -> ResponseRoleDto.builder()
                        .Role(a.getRole())
                        .authorities(a.getAuthorities())
                        .build())
                .toList();

        return rolesDto;
    }

    @Override
    public ResponseRoleDto addRole(RequestRoleDto requestRoleDto) {
        var authorities = requestRoleDto.getAuthorities().stream()
                .map(a -> authorityRepository.findByAuthority(a.getAuthority())
                        .orElseThrow(() -> new AuthorityNotFoundException(a.getAuthority() + "doesn't exists")))
                .collect(Collectors.toSet());

        var responseRoleDto = ResponseRoleDto.builder()
                .Role(requestRoleDto.getRole())
                .authorities(authorities)
                .build();

        var role = Role.builder().role(requestRoleDto.getRole()).authorities(authorities).build();
        roleRepository.save(role);
        return responseRoleDto;
    }

    @Override
    public void deleteRole(String roleName) {
        var role = roleRepository.findByRole(roleName);
        if (role.isEmpty()) throw new RoleNotFoundException(roleName + " doesn't exists");
        roleRepository.delete(role.get());
    }
}
