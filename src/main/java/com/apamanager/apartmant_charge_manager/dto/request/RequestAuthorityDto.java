package com.apamanager.apartmant_charge_manager.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RequestAuthorityDto {
    @NotBlank(message = "authority can't be blank.")
    private String authority;
}
