package org.example.model.chat;

import lombok.Data;
import org.example.model.user.User;

import java.util.List;

@Data
public class Chat {
    private User student;
    private User employee;
    private List<Message> messages;
}