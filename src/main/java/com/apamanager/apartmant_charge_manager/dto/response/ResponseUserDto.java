package com.apamanager.apartmant_charge_manager.dto.response;

import com.apamanager.apartmant_charge_manager.entity.Building;
import com.apamanager.apartmant_charge_manager.entity.Role;
import com.apamanager.apartmant_charge_manager.entity.Unit;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseUserDto {

    private String username;
    private Set<Role> roles;
    private Building building;
    private Unit unit;

}
