package com.apamanager.apartmant_charge_manager.repository;

import com.apamanager.apartmant_charge_manager.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
    public Optional<Authority> findByAuthority(String authorityName);

}
