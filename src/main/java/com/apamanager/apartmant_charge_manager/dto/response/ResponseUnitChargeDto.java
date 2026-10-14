package com.apamanager.apartmant_charge_manager.dto.response;

import com.apamanager.apartmant_charge_manager.entity.Charge;
import com.apamanager.apartmant_charge_manager.entity.Unit;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ResponseUnitChargeDto {
    private Charge charge;
    private Unit unit;
    private BigDecimal amountOwed;
    private boolean isPaid;
    private LocalDateTime paidAt;

}
