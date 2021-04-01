package com.dbryzz.ecoms.domain.repository;

import com.dbryzz.ecoms.domain.constant.UserRole;
import com.dbryzz.ecoms.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(UserRole name);
}
