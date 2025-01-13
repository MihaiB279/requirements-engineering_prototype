package org.example.service;

import org.example.model.chat.Chat;
import org.example.model.chat.Message;
import org.example.model.user.Student;
import org.example.model.user.Employee;
import org.example.repository.ChatRepository;
import org.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    private final MessageRepository messageRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    public void addMessage(Student student, Employee employee, String content) {
        Optional<Chat> chatOptional = chatRepository.findByStudentAndEmployee(student, employee);
        Chat chat;

        if (chatOptional.isPresent()) {
            chat = chatOptional.get();
        } else {
            chat = new Chat(student, employee);
            chat = chatRepository.save(chat);
        }

        Message message = new Message();
        message.setChat(chat);
        message.setSender(student);
        message.setContent(content);
        message.setTimestamp(java.time.LocalDateTime.now());
        messageRepository.save(message);
    }

    public Chat getChatForUser(Student student, Employee employee) {
        return chatRepository.findByStudentAndEmployee(student, employee)
                .orElseThrow(() -> new RuntimeException("Chat not found for student with employee"));

    }

    public List<Chat> getChatsForUser(Student student) {
        return chatRepository.findByStudent(student);
    }
}
