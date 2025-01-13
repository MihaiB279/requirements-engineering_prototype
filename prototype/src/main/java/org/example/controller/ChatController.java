package org.example.controller;

import org.example.dtos.ChatDTO;
import org.example.dtos.MessageDTO;
import org.example.dtos.MessageRequest;
import org.example.model.chat.Chat;
import org.example.model.chat.Message;
import org.example.model.user.Employee;
import org.example.model.user.Student;
import org.example.repository.EmployeeRepository;
import org.example.repository.StudentRepository;
import org.example.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/addMessage")
    public void addMessageToChat(@RequestBody MessageRequest messageRequest) {
        Long senderId = messageRequest.getSenderId();
        Long receiverId = messageRequest.getReceiverId();
        String content = messageRequest.getContent();

        Student student = null;
        Employee employee = null;
        if (messageRequest.getSenderType().equals("student")) {
            student = studentRepository.findById(senderId)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
            employee = employeeRepository.findById(receiverId)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
        } else {
            student = studentRepository.findById(receiverId)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
            employee = employeeRepository.findById(senderId)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
        }


        chatService.addMessage(student, employee, content);
    }

    @GetMapping("/{studentId}/{employeeId}")
    public ChatDTO getChatForUser(
            @PathVariable Long studentId,
            @PathVariable Long employeeId) {

        Student student = studentRepository.findById(studentId).orElse(null);
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        Chat chat = chatService.getChatForUser(student, employee);

        // Convert the chat and messages to ChatDTO
        List<MessageDTO> messageDTOs = chat.getMessages().stream()
                .map(this::convertMessageToDTO)
                .collect(Collectors.toList());

        // Return the ChatDTO
        return new ChatDTO(chat.getId(), student.getId(), employee.getId(), messageDTOs);
    }


    @GetMapping("/{studentId}")
    public List<Chat> getChatsForUser(@PathVariable Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return chatService.getChatsForUser(student);
    }

    private MessageDTO convertMessageToDTO(Message message) {
        String senderType = message.getSender() instanceof Student ? "student" : "employee";
        return new MessageDTO(
                message.getId(),
                message.getContent(),
                message.getTimestamp(),
                message.getSender().getId(),
                senderType
        );

    }
}
