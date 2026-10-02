package com.apamanager.apartmant_charge_manager.repository;

import com.apamanager.apartmant_charge_manager.entity.Authority;
import com.apamanager.apartmant_charge_manager.entity.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Set;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    private Authority authority;
    private Role role;

    @BeforeEach
    public void init() {
        authority = Authority.builder().authority("authority").build();
        authorityRepository.save(authority);
        role = Role.builder().role("role").authorities(Set.of(authority)).build();
        roleRepository.save(role);
    }

    @Test
    public void findRole() {
        var test = roleRepository.findByRole(role.getRole());

        Assertions.assertThat(test).isPresent();
        Assertions.assertThat(test.get().getRole()).isEqualTo(role.getRole());
        Assertions.assertThat(test.get().getAuthorities()).isEqualTo(role.getAuthorities());
    }

}
