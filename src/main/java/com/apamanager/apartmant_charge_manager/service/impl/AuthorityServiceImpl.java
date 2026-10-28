package com.apamanager.apartmant_charge_manager.service.impl;

import com.apamanager.apartmant_charge_manager.dto.request.RequestAuthorityDto;
import com.apamanager.apartmant_charge_manager.dto.response.ResponseAuthorityDto;
import com.apamanager.apartmant_charge_manager.entity.Authority;
import com.apamanager.apartmant_charge_manager.exceptions.AuthorityNotFoundException;
import com.apamanager.apartmant_charge_manager.repository.AuthorityRepository;
import com.apamanager.apartmant_charge_manager.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public ResponseAuthorityDto findAuthorityByName(String authorityName) {
        var authority = authorityRepository.findByAuthority(authorityName);
        if (authority.isPresent()) {
            var authorityDto = ResponseAuthorityDto.builder()
                    .authority(authority.get().getAuthority()).build();
            return authorityDto;
        } else
            throw new AuthorityNotFoundException(authorityName + " doesn't exists");
    }

    @Override
    public List<ResponseAuthorityDto> findAll() {
        var authorities = authorityRepository.findAll();
        var authorityDtos = authorities.stream().map(a -> new ResponseAuthorityDto()).toList();
        return authorityDtos;

    }

    @Override
    public ResponseAuthorityDto addAuthority(RequestAuthorityDto requestAuthorityDto) {
        var responseAuthorityDto = ResponseAuthorityDto.builder()
                .authority(requestAuthorityDto.getAuthority()).build();

        var authority = Authority.builder()
                .authority(requestAuthorityDto.getAuthority()).build();

        authorityRepository.save(authority);
        return responseAuthorityDto;
    }

    @Override
    public void deleteAuthority(String authorityName) {
        var authority = authorityRepository.findByAuthority(authorityName);
        if (authority.isPresent()) authorityRepository.delete(authority.get());
        else throw new AuthorityNotFoundException(authorityName + " doesn't exists");
    }
}
