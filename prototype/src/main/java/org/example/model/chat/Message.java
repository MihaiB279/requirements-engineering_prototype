package org.example.model.chat;

import javax.persistence.*;  // Folosim javax.persistence pentru Spring Boot 2.x
import lombok.Data;
import org.example.model.user.User;

import java.time.LocalDateTime;

@Data
@Entity  // Marcați clasa ca entitate JPA
public class Message {

    @Id  // Câmpul id va fi cheia primară
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Cheia primară va fi generată automat (auto-increment)
    private Long id;  // Tipul Long este cel mai frecvent folosit pentru ID-uri

    @ManyToOne(fetch = FetchType.LAZY)  // Relație ManyToOne între Message și User (sender)
    @JoinColumn(name = "sender_id")  // Columna care va stoca id-ul utilizatorului
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)  // Relație ManyToOne între Message și User (reciver)
    @JoinColumn(name = "reciver_id")  // Columna care va stoca id-ul utilizatorului
    private User receiver;

    private String content;  // Conținutul mesajului

    private LocalDateTime timestamp;  // Timpul în care a fost trimis mesajul

    // Constructori
    public Message(User sender,User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.now();  // Setează timpul actual
    }

    // Constructor implicit pentru JPA
    public Message() {
    }


}
