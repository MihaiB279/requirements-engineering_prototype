package org.example.repository;

import org.example.model.user.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserApp, Long> {

    Optional<UserApp> findByEmailOrUsername(String email, String username);
}