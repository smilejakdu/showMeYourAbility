package com.example.showmeyourability.reply.infrastructure.dto;

import lombok.Data;

@Data
public class CreateReplyRequestDto {
    private Long commentId;
    private String content;
}
