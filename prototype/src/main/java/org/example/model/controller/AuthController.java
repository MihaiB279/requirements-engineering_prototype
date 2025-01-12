package org.example.model.controller;


import org.example.model.service.UserService;
import org.example.model.user.Employee;
import org.example.model.user.Student;
import org.example.model.user.User;
import org.example.model.user.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint pentru logare
    @PostMapping("/login")
    public String login(@RequestParam String emailOrUsername, @RequestParam String password) {
        boolean authenticated = userService.authenticate(emailOrUsername, password);
        if (authenticated) {
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }

    // Endpoint pentru crearea unui utilizator nou
    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String username, @RequestParam String password ,@RequestParam UserType userType) {
        User user = null;
        if(userType== UserType.STUDENT){
             user = new Student();
        }
        if(userType== UserType.EMPLOYEE){
            user = new Employee();
        }
        if(userType== UserType.RECRUITER){
            user = new Student();
        }
        user.setEmail(email);
        user.setUsername(username);
        userService.createUser(user, password);
        return "User registered successfully!";
    }

    // Endpoint pentru logout
    @PostMapping("/logout")
    public String logout() {
        // Spring Security se ocupă de logout în mod automat
        SecurityContextHolder.clearContext();  // Ștergem contextul de securitate
        return "Logout successful!";
    }
}
