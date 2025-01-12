package org.example.model.service;



import org.example.model.chat.Message;
import org.example.model.repository.MessageRepository;
import org.example.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Găsește toate mesajele din baza de date.
     * @return Set de mesaje.
     */
    public Set<Message> findAllMessages() {
        return messageRepository.findAll();
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

    public List<Message> getMyMessages(Long myID, Long friendID) {
        List<Message> messages = new ArrayList<>();
        for (Message m : this.messageRepository.findAll()) {
            if ((Objects.equals(m.getSender().getId(), myID) && (Objects.equals(m.getReceiver().getId(), friendID))) || (Objects.equals(m.getSender().getId(), friendID) && (Objects.equals(m.getReceiver().getId(), myID)))) {
                messages.add(m);
            }
        }
        messages.sort(Comparator.comparing(Message::getTimestamp));

        return messages;
    }



}
