package com.apamanager.apartmant_charge_manager.repository;

import com.apamanager.apartmant_charge_manager.entity.*;
import com.apamanager.apartmant_charge_manager.enums.ChargeType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ChargeRepositoryTest {

    @Autowired
    private ChargeRepository chargeRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UnitRepository unitRepository;

    private Authority authority;
    private Role role;
    private User user;
    private Building building;
    private Unit unit;
    private Charge charge;

    @BeforeEach
    public void init() {
        authority = Authority.builder().authority("authority").build();
        authorityRepository.save(authority);

        role = Role.builder().role("role").authorities(Set.of(authority)).build();
        roleRepository.save(role);

        unit = Unit.builder().user(user).number("4").householdSize(5).debt(BigDecimal.valueOf(0)).building(building).
                build();
        unitRepository.save(unit);

        building = Building.builder().name("name").address("address").units(Set.of(unit)).build();
        buildingRepository.save(building);

        user = User.builder().username("username").password("password").building(building).unit(unit).roles(Set.of(role)).build();
        userRepository.save(user);

        charge = Charge.builder().title("title").amount(BigDecimal.valueOf(100)).dateTime(LocalDateTime.now())
                .chargeType(ChargeType.FIXED).building(building).build();
        chargeRepository.save(charge);
    }
/*
    @Test
    public void findChargeByTitle_OptionalOfCharge() {
        var test = chargeRepository.findChargeByTitle(charge.getTitle());

        Assertions.assertThat(test).isPresent();
        Assertions.assertThat(test.get()).isEqualTo(charge);
    }*/
}
