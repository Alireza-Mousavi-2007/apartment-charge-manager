package com.apamanager.apartmant_charge_manager.dto.request;

import com.apamanager.apartmant_charge_manager.entity.Unit;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RequestBuildingDto {
    @NotBlank(message = "name can't be blank.")
    private String name;

    @NotBlank(message = "address can't be blank.")
    private String address;
    private Set<Unit> units;
}
