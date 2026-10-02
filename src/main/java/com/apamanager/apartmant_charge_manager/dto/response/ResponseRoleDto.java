package com.apamanager.apartmant_charge_manager.dto.response;

import com.apamanager.apartmant_charge_manager.entity.Authority;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseRoleDto {

    private String Role;
    private Set<Authority> authorities;
}
