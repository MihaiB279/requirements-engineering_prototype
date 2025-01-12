package org.example.model.user;

import javax.persistence.*;  // Folosim javax.persistence pentru Spring Boot 2.x
import lombok.Data;

@Data
@Entity  // Marcați User ca entitate JPA
@Inheritance(strategy = InheritanceType.JOINED)  // Permite utilizarea moștenirii între User și subclasele sale
public abstract class User {

    @Id  // Adnotăm câmpul id ca fiind cheia primară
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Generează valoarea id automat în funcție de baza de date
    private Long id;

    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)  // Presupunem că UserType este un enum
    private UserType type;

    // Constructori, metode etc. pot fi adăugați în funcție de necesități
}
