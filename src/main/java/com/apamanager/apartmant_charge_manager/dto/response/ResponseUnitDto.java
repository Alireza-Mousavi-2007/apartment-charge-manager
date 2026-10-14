package com.apamanager.apartmant_charge_manager.dto.response;

import com.apamanager.apartmant_charge_manager.entity.Building;
import com.apamanager.apartmant_charge_manager.entity.User;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ResponseUnitDto {

    private String number;
    private Integer householdSize;
    private Building building;
    private User user;
    private BigDecimal debt;

}
