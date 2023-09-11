package com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto;


import lombok.Builder;
import lombok.Data;

@Data
public class ReplyDto {
    private Long replyId;
    private Long userId;
    private String email;
    private String content;
    private String createdAt;

    @Builder
    public ReplyDto(
            Long replyId,
            Long userId,
            String email,
            String content,
            String createdAt
    ) {
        this.replyId = replyId;
        this.userId = userId;
        this.email = email;
        this.content = content;
        this.createdAt = createdAt;
    }
}
