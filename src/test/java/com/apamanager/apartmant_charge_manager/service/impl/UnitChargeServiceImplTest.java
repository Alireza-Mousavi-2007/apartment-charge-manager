package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestUnitChargeDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseUnitChargeDto;
import com.apamanager.apartmant_charge_manager.entity.*;
import com.apamanager.apartmant_charge_manager.enums.ChargeType;
import com.apamanager.apartmant_charge_manager.repository.UnitChargeRepository;
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
public class UnitChargeServiceImplTest {

    @Mock
    private UnitChargeRepository unitChargeRepository;
    @InjectMocks
    private UnitChargeServiceImpl unitChargeService;

    private Authority authority;
    private Role role;
    private User user;
    private Building building;
    private Unit unit;
    private Charge charge;
    private UnitCharge unitCharge;
    private ResponseUnitChargeDto responseUnitChargeDto;
    private RequestUnitChargeDto requestUnitChargeDto;

    @BeforeEach
    public void init() {
        authority = Authority.builder().authority("authority").build();
        role = Role.builder().role("role").authorities(Set.of(authority)).build();
        building = Building.builder().name("name").address("address").build();
        unit = Unit.builder().number("number").householdSize(1).building(building).user(user).debt(BigDecimal.valueOf(1)).build();
        user = User.builder().id(1).username("username").password("password").roles(Set.of(role)).building(building).unit(unit).build();
        charge = Charge.builder().id(1).title("title").chargeType(ChargeType.FIXED).dateTime(LocalDateTime.now()).building(building).amount(BigDecimal.valueOf(1)).build();
        unitCharge = UnitCharge.builder().id(1).charge(charge).unit(unit).amountOwed(BigDecimal.valueOf(0)).isPaid(true).paidAt(LocalDateTime.now()).build();
        responseUnitChargeDto = ResponseUnitChargeDto.builder().charge(charge).unit(unit).amountOwed(BigDecimal.valueOf(0)).isPaid(true).paidAt(LocalDateTime.now()).build();
        requestUnitChargeDto = RequestUnitChargeDto.builder().charge(charge).unit(unit).amountOwed(BigDecimal.valueOf(0)).isPaid(true).paidAt(LocalDateTime.now()).build();
    }

    @Test
    public void findById_found_responseUnitChargeDto() {
        when(unitChargeRepository.findById(anyInt())).thenReturn(Optional.of(unitCharge));

        var test = unitChargeService.findById(unitCharge.getId());

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getUnit()).isEqualTo(unitCharge.getUnit());
        Assertions.assertThat(test.getCharge()).isEqualTo(unitCharge.getCharge());
        Assertions.assertThat(test.getAmountOwed()).isEqualTo(unitCharge.getAmountOwed());
        Assertions.assertThat(test.getPaidAt()).isEqualTo(unitCharge.getPaidAt());

    }

    @Test
    public void findAll_found_listOfResponseUnitChargeDto() {
        when(unitChargeRepository.findAll()).thenReturn(List.of(unitCharge));

        var test = unitChargeService.findAll();

        Assertions.assertThat(test).isNotEmpty();
        Assertions.assertThat(test.size()).isGreaterThan(0);
        Assertions.assertThat(test).extracting(a -> a.getUnit()).contains(unitCharge.getUnit());
        Assertions.assertThat(test).extracting(a -> a.getCharge()).contains(unitCharge.getCharge());
        Assertions.assertThat(test).extracting(a -> a.getPaidAt()).contains(unitCharge.getPaidAt());

    }

    @Test
    public void addUnitCharge_added_responseUnitChargeDto() {
        var test = unitChargeService.addUnitCharge(requestUnitChargeDto);
        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getUnit()).isEqualTo(unitCharge.getUnit());
        Assertions.assertThat(test.getCharge()).isEqualTo(unitCharge.getCharge());
        Assertions.assertThat(test.getAmountOwed()).isEqualTo(unitCharge.getAmountOwed());

    }

    @Test
    public void deleteUnitCharge_deleted_void() {
        when(unitChargeRepository.findById(anyInt())).thenReturn(Optional.of(unitCharge));

        unitChargeService.deleteCharge(unitCharge.getId());

        verify(unitChargeRepository,times(1)).delete(any());
    }
}
