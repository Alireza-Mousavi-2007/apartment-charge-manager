package com.apamanager.apartmant_charge_manager.dto.response;

import com.apamanager.apartmant_charge_manager.entity.Building;
import com.apamanager.apartmant_charge_manager.enums.ChargeType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ResponseChargeDto {

    private String title;
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private ChargeType chargeType;
    private Building building;

}
