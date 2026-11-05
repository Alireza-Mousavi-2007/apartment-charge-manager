package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestUnitDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseUnitDto;
import com.apamanager.apartmant_charge_manager.entity.*;
import com.apamanager.apartmant_charge_manager.repository.UnitRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnitServiceImplTest {

    @Mock
    private UnitRepository unitRepository;
    @InjectMocks
    private UnitServiceImpl unitService;

    private Authority authority;
    private Role role;
    private User user;
    private Building building;
    private Unit unit;
    private ResponseUnitDto responseUnitDto;
    private RequestUnitDto requestUnitDto;

    @BeforeEach
    public void init() {
        authority = Authority.builder().authority("authority").build();
        role = Role.builder().role("role").authorities(Set.of(authority)).build();
        building = Building.builder().name("name").address("address").build();
        user = User.builder().id(1).username("username").password("password").roles(Set.of(role)).building(building).unit(unit).build();
        unit = Unit.builder().number("number").householdSize(1).building(building).user(user).debt(BigDecimal.valueOf(1)).build();
        responseUnitDto = ResponseUnitDto.builder().number("number").householdSize(1).building(building).user(user).debt(BigDecimal.valueOf(1)).build();
        requestUnitDto = RequestUnitDto.builder().number("number").householdSize(1).building(building).user(user).debt(BigDecimal.valueOf(1)).build();
    }


    @Test
    public void findUnitByNumber_found_responseUnitDto() {
        when(unitRepository.findUnitByNumber(anyString())).thenReturn(Optional.of(unit));

        var test = unitService.findUnitByNumber(unit.getNumber());

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getNumber()).isEqualTo(unit.getNumber());
        Assertions.assertThat(test.getBuilding()).isEqualTo(unit.getBuilding());
        Assertions.assertThat(test.getUser()).isEqualTo(unit.getUser());
        Assertions.assertThat(test.getDebt()).isEqualTo(unit.getDebt());
        Assertions.assertThat(test.getHouseholdSize()).isEqualTo(unit.getHouseholdSize());

    }


    @Test
    public void findAll_found_listOfResponseUnitDto() {
        when(unitRepository.findAll()).thenReturn(List.of(unit));

        var test = unitService.findAll();

        Assertions.assertThat(test).isNotEmpty();
        Assertions.assertThat(test.size()).isGreaterThan(0);
        Assertions.assertThat(test).extracting(a -> a.getBuilding()).contains(unit.getBuilding());
    }

    @Test
    public void addUnit_added_responseUnitDto() {

        var test = unitService.addUnit(requestUnitDto);

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getNumber()).isEqualTo(unit.getNumber());
        Assertions.assertThat(test.getBuilding()).isEqualTo(unit.getBuilding());
        Assertions.assertThat(test.getUser()).isEqualTo(unit.getUser());
        Assertions.assertThat(test.getDebt()).isEqualTo(unit.getDebt());
        Assertions.assertThat(test.getHouseholdSize()).isEqualTo(unit.getHouseholdSize());

    }

    @Test
    public void deleteUnit_deleted_void() {
        when(unitRepository.findUnitByNumber(anyString())).thenReturn(Optional.of(unit));

        unitService.deleteUnit(unit.getNumber());

        verify(unitRepository , times(1)).delete(any());

    }

    @Test
    public void updateUnitByNumber_updated_responseUnitDto(){
        when(unitRepository.findUnitByNumber(anyString())).thenReturn(Optional.of(unit));

        var test = unitService.updateUnitByNumber(unit.getNumber(),requestUnitDto);

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getNumber()).isEqualTo(unit.getNumber());
        Assertions.assertThat(test.getBuilding()).isEqualTo(unit.getBuilding());
        Assertions.assertThat(test.getUser()).isEqualTo(unit.getUser());
        Assertions.assertThat(test.getDebt()).isEqualTo(unit.getDebt());
        Assertions.assertThat(test.getHouseholdSize()).isEqualTo(unit.getHouseholdSize());

    }

}
