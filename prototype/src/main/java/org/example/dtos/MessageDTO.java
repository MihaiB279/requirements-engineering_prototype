package org.example.dtos;

import java.time.LocalDateTime;

public class MessageDTO {

    private Long id;
    private String content;
    private LocalDateTime timestamp;
    private Long senderId;
    private String senderType;

    public MessageDTO(Long id, String content, LocalDateTime timestamp, Long senderId, String senderType) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.senderId = senderId;
        this.senderType = senderType;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }
}
