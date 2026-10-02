package com.apamanager.apartmant_charge_manager.dto.request;

import com.apamanager.apartmant_charge_manager.entity.Authority;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestRoleDto {
    private String Role;
    private Set<Authority> authorities;
}
