package com.example.showmeyourability.reply.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateReplyResponseDto {
    private Long commentId;
    private Long userId;
    private String email;
    private Long replyId;
    private String content;
}
