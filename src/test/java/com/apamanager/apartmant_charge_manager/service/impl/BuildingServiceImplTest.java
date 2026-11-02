package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestBuildingDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseBuildingDto;
import com.apamanager.apartmant_charge_manager.entity.*;
import com.apamanager.apartmant_charge_manager.repository.BuildingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuildingServiceImplTest {

    @Mock
    private BuildingRepository buildingRepository;
    @InjectMocks
    private BuildingServiceImpl buildingService;


    private Authority authority;
    private Role role;
    private User user;
    private Building building;
    private Unit unit;
    private ResponseBuildingDto responseBuildingDto;
    private RequestBuildingDto requestBuildingDto;

    @BeforeEach
    public void init() {
        authority = Authority.builder().authority("authority").build();
        role = Role.builder().role("role").authorities(Set.of(authority)).build();
        building = Building.builder().name("name").address("address").build();
        unit = Unit.builder().number("number").householdSize(1).building(building).user(user).debt(BigDecimal.valueOf(1)).build();
        user = User.builder().id(1).username("username").password("password").roles(Set.of(role)).building(building).unit(unit).build();
        responseBuildingDto = ResponseBuildingDto.builder().name("name").address("address").units(Set.of(unit)).build();
        requestBuildingDto = RequestBuildingDto.builder().name("name").address("address").units(Set.of(unit)).build();
    }

    @Test
    public void findBuildingByName_found_responseBuildingDto() {
        when(buildingRepository.findBuildingByName(anyString())).thenReturn(Optional.of(building));

        var test = buildingService.findBuildingByName(building.getName());

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getName()).isEqualTo(building.getName());
        Assertions.assertThat(test.getAddress()).isEqualTo(building.getAddress());

    }

    @Test
    public void findBuildingByAddress_found_responseBuildingDto() {
        when(buildingRepository.findBuildingByAddress(anyString())).thenReturn(Optional.of(building));

        var test = buildingService.findBuildingByAddress(building.getAddress());

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getName()).isEqualTo(building.getName());
        Assertions.assertThat(test.getAddress()).isEqualTo(building.getAddress());
    }

    @Test
    public void findAll_found_listOfResponseBuildingDto() {
        when(buildingRepository.findAll()).thenReturn(List.of(building));

        var test = buildingService.findAll();

        Assertions.assertThat(test).isNotEmpty();
        Assertions.assertThat(test.size()).isGreaterThan(0);
        Assertions.assertThat(test).extracting(a -> a.getName()).contains(building.getName());

    }

    


}
