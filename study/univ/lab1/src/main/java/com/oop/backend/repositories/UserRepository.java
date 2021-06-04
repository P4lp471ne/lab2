package com.oop.backend.repositories;

import com.oop.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByUsernameStartsWith(String prefix);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
