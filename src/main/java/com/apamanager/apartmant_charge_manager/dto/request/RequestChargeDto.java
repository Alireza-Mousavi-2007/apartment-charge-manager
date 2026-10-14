package com.apamanager.apartmant_charge_manager.dto.request;

import com.apamanager.apartmant_charge_manager.entity.Building;
import com.apamanager.apartmant_charge_manager.enums.ChargeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RequestChargeDto {

    @NotBlank(message = "title can't be empty")
    private String title;

    @NotNull(message = "amount can't be empty")
    @PositiveOrZero
    private BigDecimal amount;

    @NotNull(message = "dateTime can't be empty")
    private LocalDateTime dateTime;

    @NotNull(message = "dateTime can't be empty")
    private ChargeType chargeType;

    @NotNull(message = "dateTime can't be empty")
    private Building building;

}
