package org.example.model.service;

import org.example.model.repository.UserRepository;
import org.example.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Verificăm autentificarea unui utilizator pe baza emailului/username-ului și a parolei
    public boolean authenticate(String emailOrUsername, String password) {
        Optional<User> userOpt = userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return passwordEncoder.matches(password, user.getPassword());  // Comparăm parola criptată
        }
        return false;
    }

    // Crearea unui user nou
    public User createUser(User user, String rawPassword) {
        String encryptedPassword = passwordEncoder.encode(rawPassword);  // Criptăm parola
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }
}
