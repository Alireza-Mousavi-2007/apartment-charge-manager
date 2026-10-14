package com.apamanager.apartmant_charge_manager.dto.response;

import com.apamanager.apartmant_charge_manager.entity.Unit;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ResponseBuildingDto {
    private String name;
    private String address;
    private Set<Unit> units;
}
