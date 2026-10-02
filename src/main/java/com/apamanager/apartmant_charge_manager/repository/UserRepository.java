package com.apamanager.apartmant_charge_manager.repository;

import com.apamanager.apartmant_charge_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);

}
