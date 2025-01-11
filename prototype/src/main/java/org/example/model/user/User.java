package org.example.model.user;

import lombok.Data;

@Data
public abstract class User {
    private String email;
    private String username;
    private String password;
    private UserType type;
}