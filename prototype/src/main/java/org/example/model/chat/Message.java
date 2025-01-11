package org.example.model.chat;

import lombok.Data;
import org.example.model.user.User;

import java.time.LocalDateTime;

@Data
public class Message {
    private User sender;
    private String content;
    private LocalDateTime timestamp;
}