package com.apamanager.apartmant_charge_manager.dto.request;

import com.apamanager.apartmant_charge_manager.entity.Building;
import com.apamanager.apartmant_charge_manager.entity.Role;
import com.apamanager.apartmant_charge_manager.entity.Unit;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestUserUpdateDto {
    @NotBlank(message = "username can't be blank.")
    private String username;
    @NotBlank(message = "password can't be blank.")
    private String password;
}
