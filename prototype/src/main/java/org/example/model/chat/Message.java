package org.example.model.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.example.model.user.UserApp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Chat chat;

    @ManyToOne
    @JsonIgnore
    private UserApp sender;

    private String content;
    private LocalDateTime timestamp;

    public Message() {
        this.timestamp = LocalDateTime.now();
    }

    public Message(UserApp sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }
}
