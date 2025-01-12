package org.example.service;



import org.example.model.chat.Message;
import org.example.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    /**
     * Găsește un mesaj după ID.
     * @param messageId ID-ul mesajului.
     * @return Optional care conține mesajul dacă este găsit.
     */
    public Optional<Message> findMessageById(Long messageId) {
        return messageRepository.findById(messageId);
    }

    /**
     * Creează și salvează un mesaj nou.
     * @param message Entitatea `Message` de salvat.
     * @return Mesajul salvat cu ID-ul generat.
     */
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

}
