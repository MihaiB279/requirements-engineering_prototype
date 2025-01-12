package org.example.controller;

import org.example.dtos.MessageRequest;
import org.example.model.chat.Chat;
import org.example.model.user.Employee;
import org.example.model.user.Student;
import org.example.repository.EmployeeRepository;
import org.example.repository.StudentRepository;
import org.example.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if(messageRequest.getSenderType().equals("student")) {
            student = studentRepository.findById(senderId)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
            employee = employeeRepository.findById(receiverId)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
        }
        else {
            student = studentRepository.findById(receiverId)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
            employee = employeeRepository.findById(senderId)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
        }


        chatService.addMessage(student, employee, content);
    }

    @GetMapping("/{studentId}/{employeeId}")
    public Chat getChatForUser(
            @PathVariable Long studentId,
            @PathVariable Long employeeId) {

        Student student = studentRepository.findById(studentId).orElse(null);
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        return chatService.getChatForUser(student, employee);
    }
}
