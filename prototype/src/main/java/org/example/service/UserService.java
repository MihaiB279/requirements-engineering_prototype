package org.example.service;

import org.example.repository.UserRepository;
import org.example.model.user.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String emailOrUsername, String password) {
        Optional<UserApp> userOpt = userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername);

        if (userOpt.isPresent()) {
            UserApp user = userOpt.get();
            return password.equals(user.getPassword());
        }
        return false;
    }

    public UserApp createUser(UserApp user, String password) {
        user.setPassword(password);
        return userRepository.save(user);
    }
}