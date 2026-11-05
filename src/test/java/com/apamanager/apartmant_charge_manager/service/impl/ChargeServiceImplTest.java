package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestChargeDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseChargeDto;
import com.apamanager.apartmant_charge_manager.entity.*;
import com.apamanager.apartmant_charge_manager.enums.ChargeType;
import com.apamanager.apartmant_charge_manager.repository.ChargeRepository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChargeServiceImplTest {

    @Mock
    private ChargeRepository chargeRepository;
    @InjectMocks
    private ChargeServiceImpl chargeService;


    private Authority authority;
    private Role role;
    private User user;
    private Building building;
    private Unit unit;
    private Charge charge;
    private ResponseChargeDto responseChargeDto;
    private RequestChargeDto requestChargeDto;

    @BeforeEach
    public void init() {
        authority = Authority.builder().authority("authority").build();
        role = Role.builder().role("role").authorities(Set.of(authority)).build();
        building = Building.builder().name("name").address("address").build();
        unit = Unit.builder().number("number").householdSize(1).building(building).user(user).debt(BigDecimal.valueOf(1)).build();
        user = User.builder().id(1).username("username").password("password").roles(Set.of(role)).building(building).unit(unit).build();
        charge = Charge.builder().id(1).title("title").chargeType(ChargeType.FIXED).dateTime(LocalDateTime.now()).building(building).amount(BigDecimal.valueOf(1)).build();
        requestChargeDto = RequestChargeDto.builder().title("title").chargeType(ChargeType.FIXED).dateTime(LocalDateTime.now()).building(building).amount(BigDecimal.valueOf(1)).build();
        responseChargeDto = ResponseChargeDto.builder().title("title").chargeType(ChargeType.FIXED).dateTime(LocalDateTime.now()).building(building).amount(BigDecimal.valueOf(1)).build();
    }

    @Test
    public void findChargeById_found_responseChargeDto() {
        when(chargeRepository.findChargeById(anyInt())).thenReturn(Optional.of(charge));

        var test = chargeService.findChargeById(charge.getId());

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getTitle()).isEqualTo(charge.getTitle());
        Assertions.assertThat(test.getAmount()).isEqualTo(charge.getAmount());
        Assertions.assertThat(test.getBuilding()).isEqualTo(charge.getBuilding());
        Assertions.assertThat(test.getDateTime()).isEqualTo(charge.getDateTime());
        Assertions.assertThat(test.getChargeType()).isEqualTo(charge.getChargeType());
    }

    @Test
    public void findAll_found_listOfResponseChargeDto() {
        when(chargeRepository.findAll()).thenReturn(List.of(charge));

        var test = chargeService.findAll();

        Assertions.assertThat(test).isNotEmpty();
        Assertions.assertThat(test.size()).isGreaterThan(0);
        Assertions.assertThat(test).extracting(a -> a.getTitle()).contains(charge.getTitle());
        Assertions.assertThat(test).extracting(a -> a.getBuilding()).contains(charge.getBuilding());

    }

    @Test
    public void addCharge_added_responseChargeDto() {
        var test = chargeService.addCharge(requestChargeDto);

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getTitle()).isEqualTo(charge.getTitle());
        Assertions.assertThat(test.getAmount()).isEqualTo(charge.getAmount());
        Assertions.assertThat(test.getBuilding()).isEqualTo(charge.getBuilding());
        Assertions.assertThat(test.getDateTime()).isEqualTo(charge.getDateTime());
        Assertions.assertThat(test.getChargeType()).isEqualTo(charge.getChargeType());
    }

    @Test
    public void updateChargeById_updated_responseChargeDto() {
        when(chargeRepository.findChargeById(anyInt())).thenReturn(Optional.of(charge));

        var test = chargeService.updateChargeById(charge.getId(), requestChargeDto);

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getTitle()).isEqualTo(requestChargeDto.getTitle());
        Assertions.assertThat(test.getAmount()).isEqualTo(requestChargeDto.getAmount());
        Assertions.assertThat(test.getBuilding()).isEqualTo(requestChargeDto.getBuilding());
        Assertions.assertThat(test.getDateTime()).isEqualTo(requestChargeDto.getDateTime());
        Assertions.assertThat(test.getChargeType()).isEqualTo(requestChargeDto.getChargeType());

    }

    @Test
    public void deleteChargeById_deleted_void() {
        when(chargeRepository.findChargeById(anyInt())).thenReturn(Optional.of(charge));
        chargeService.deleteChargeById(charge.getId());

        verify(chargeRepository, times(1)).delete(any());
    }
}
