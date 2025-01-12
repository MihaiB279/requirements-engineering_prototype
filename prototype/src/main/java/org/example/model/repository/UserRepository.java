package org.example.model.repository;

import org.example.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Căutăm un user pe baza emailului sau a username-ului
    Optional<User> findByEmailOrUsername(String email, String username);
}
