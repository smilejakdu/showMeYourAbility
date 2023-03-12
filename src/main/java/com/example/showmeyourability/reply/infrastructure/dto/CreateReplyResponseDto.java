package com.example.showmeyourability.reply.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateReplyResponseDto {
    private Long commentId;
    private Long userId;
    private String email;
    private Long replyId;
    private String content;

    @Builder
    public CreateReplyResponseDto(
            Long commentId,
            Long userId,
            String email,
            Long replyId,
            String content
    ) {
        this.userId = userId;
        this.email = email;
        this.commentId = commentId;
        this.replyId = replyId;
        this.content = content;
    }
}
