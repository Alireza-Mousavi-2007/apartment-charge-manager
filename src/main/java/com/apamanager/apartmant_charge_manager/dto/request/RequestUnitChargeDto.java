package com.apamanager.apartmant_charge_manager.dto.request;

import com.apamanager.apartmant_charge_manager.entity.Charge;
import com.apamanager.apartmant_charge_manager.entity.Unit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RequestUnitChargeDto {

    @NotNull(message = "charge can't be blank.")
    private Charge charge;

    @NotNull(message = "charge can't be blank.")
    private Unit unit;

    @NotNull(message = "charge can't be blank.")
    private BigDecimal amountOwed;

    @NotNull(message = "charge can't be blank.")
    private boolean isPaid;

    @NotNull(message = "charge can't be blank.")
    private LocalDateTime paidAt;

}
