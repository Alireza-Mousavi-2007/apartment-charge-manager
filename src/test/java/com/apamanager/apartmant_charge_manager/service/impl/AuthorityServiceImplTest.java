package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestAuthorityDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseAuthorityDto;
import com.apamanager.apartmant_charge_manager.entity.Authority;
import com.apamanager.apartmant_charge_manager.repository.AuthorityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorityServiceImplTest {

    @Mock
    private AuthorityRepository authorityRepository;
    @InjectMocks
    private AuthorityServiceImpl authorityService;

    Authority authority;
    RequestAuthorityDto requestAuthorityDto;
    ResponseAuthorityDto responseAuthorityDto;

    @BeforeEach
    public void init() {
        authority = Authority.builder().id(1).authority("authority").build();
        requestAuthorityDto = RequestAuthorityDto.builder().authority("authority").build();
        responseAuthorityDto = ResponseAuthorityDto.builder().authority("authority").build();
    }


    @Test
    public void findAuthorityByName_found_responseAuthorityDto() {
        when(authorityRepository.findByAuthority(anyString())).thenReturn(Optional.of(authority));

        var test = authorityService.findAuthorityByName(authority.getAuthority());

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getAuthority()).isEqualTo(authority.getAuthority());
    }

    @Test
    public void findAll_found_listOfResponseAuthorityDto() {
        when(authorityRepository.findAll()).thenReturn(List.of(authority));

        var test = authorityService.findAll();
        Assertions.assertThat(test).isNotEmpty();
        Assertions.assertThat(test)
                .extracting(a -> a.getAuthority())
                .contains(responseAuthorityDto.getAuthority());
    }

    @Test
    public void addAuthority_added_responseAuthorityDto(){
        when(authorityRepository.save(any())).thenReturn(authority);

        var test = authorityService.addAuthority(requestAuthorityDto);

        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getAuthority()).isEqualTo(responseAuthorityDto.getAuthority());

    }

    @Test
    public void deleteAuthority_deleted_void(){

        when(authorityRepository.findByAuthority(anyString())).thenReturn(Optional.of(authority));
        authorityService.deleteAuthority(authority.getAuthority());

        verify(authorityRepository,times(1)).delete(any());
    }

}
