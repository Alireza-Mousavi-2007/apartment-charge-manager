package com.apamanager.apartmant_charge_manager.dto.request;

import com.apamanager.apartmant_charge_manager.entity.Building;
import com.apamanager.apartmant_charge_manager.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RequestUnitDto {

    @NotBlank(message = "number can't be blank.")
    private String number;
    @Min(1)
    private Integer householdSize;
    @NotNull(message = "building can't be empty.")
    private Building building;
    @NotNull(message = "user can't be empty.")
    private User user;
    @NotNull(message = "debt can't be empty.")
    private BigDecimal debt;

}
