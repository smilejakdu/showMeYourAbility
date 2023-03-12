package com.example.showmeyourability.reply.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateReplyResponseDto {
    private Long replyId;
    private Long commentId;
    private Long userId;
    private String email;
    private String content;

    @Builder
    public UpdateReplyResponseDto(
            Long replyId,
            Long commentId,
            Long userId,
            String email,
            String content
    ) {
        this.replyId = replyId;
        this.commentId = commentId;
        this.userId = userId;
        this.email = email;
        this.content = content;
    }
}
