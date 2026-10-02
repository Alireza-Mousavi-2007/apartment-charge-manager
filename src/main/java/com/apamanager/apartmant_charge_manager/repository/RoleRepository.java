package com.apamanager.apartmant_charge_manager.repository;

import com.apamanager.apartmant_charge_manager.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Optional<Role> findByRole(String role);

}
