package com.gg.ecom.repository;

import com.gg.ecom.model.Role;
import com.gg.ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Iterable<User> findByRoles(Role userRoles);

    //Optional<User> findByRoles(String userRoles);

    Optional<User> findByUserNID(String userNID);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUserNID(String userNID);
}
