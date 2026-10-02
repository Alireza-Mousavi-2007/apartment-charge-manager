package com.apamanager.apartmant_charge_manager.repository;

import com.apamanager.apartmant_charge_manager.dto.response.ResponseAuthorityDto;
import com.apamanager.apartmant_charge_manager.entity.Authority;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuthorityRepositoryTest {

    @Autowired
    private AuthorityRepository authorityRepository;

    private Authority authority;
    private ResponseAuthorityDto responseAuthorityDto;

    @BeforeEach
    public void init() {
        authority = Authority.builder().authority("authority").build();
        authorityRepository.save(authority);

    }

    @Test
    public void findAuthorityByName() {
        var tested = authorityRepository.findByAuthority(authority.getAuthority());
        Assertions.assertThat(tested).isPresent();
        Assertions.assertThat(tested.get().getAuthority()).isEqualTo(authority.getAuthority());
    }



}
