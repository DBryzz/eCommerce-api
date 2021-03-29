package com.gg.ecom.repository;

import com.gg.ecom.model.ERole;
import com.gg.ecom.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ERole name);
}
