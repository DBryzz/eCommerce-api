package com.dbryzz.ecoms.domain.repository;

import com.dbryzz.ecoms.domain.model.Role;
import com.dbryzz.ecoms.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAllByUserNID(String NID);

    Optional<User> findByUsername(String username);

    Iterable<User> findByRoles(Role userRoles);

    //Optional<User> findByRoles(String userRoles);

    Optional<User> findByUserNID(String userNID);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUserNID(String userNID);
}
