package org.example.dtos;

import lombok.Data;

@Data
public class MessageRequest {
    private String senderType;
    private Long senderId;
    private Long receiverId;
    private String content;

}
