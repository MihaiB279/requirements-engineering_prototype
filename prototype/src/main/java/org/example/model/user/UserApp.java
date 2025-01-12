package org.example.model.user;

import javax.persistence.*;  // Folosim javax.persistence pentru Spring Boot 2.x
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

}
