package com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateCommentResponseDto {
    private Long commentId;
    private String content;
    private Long teacherId;
    private Long userId;

    @Builder
    public CreateCommentResponseDto(
            Long commentId,
            String content,
            Long teacherId,
            Long userId
    ) {
        this.commentId = commentId;
        this.content = content;
        this.teacherId = teacherId;
        this.userId = userId;
    }
}
