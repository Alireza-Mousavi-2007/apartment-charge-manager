package com.apamanager.apartmant_charge_manager.dto.request;

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
public class RequestUserUpdateDto {
    private String username;
    private String password;
}
