package com.yana.stepanova.repository.user;

import com.yana.stepanova.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    Optional<UserDetails> findByEmail(String email);
}
