package org.example.dtos;

import java.util.List;

public class ChatDTO {

    private Long chatId;
    private Long studentId;
    private Long employeeId;
    private List<MessageDTO> messages;

    // Constructor
    public ChatDTO(Long chatId, Long studentId, Long employeeId, List<MessageDTO> messages) {
        this.chatId = chatId;
        this.studentId = studentId;
        this.employeeId = employeeId;
        this.messages = messages;
    }

    // Getters and Setters
    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }
}
