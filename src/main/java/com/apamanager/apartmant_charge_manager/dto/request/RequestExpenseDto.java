package com.apamanager.apartmant_charge_manager.dto.request;

import com.apamanager.apartmant_charge_manager.entity.Building;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RequestExpenseDto {

    @NotBlank(message = "title can't be blank.")
    private String title;

    @NotBlank(message = "amount can't be empty")
    private BigDecimal amount;

    @NotBlank(message = "dateTime can't be empty")
    private LocalDateTime dateTime;

    @NotBlank(message = "building can't be empty")
    private Building building;
}
