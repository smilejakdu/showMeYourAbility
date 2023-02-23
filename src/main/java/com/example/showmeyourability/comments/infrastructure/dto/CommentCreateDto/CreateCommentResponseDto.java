package com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto;

import lombok.Data;

@Data
public class CreateCommentResponseDto {
    private Long commentId;
    private String content;
    private Long teacherId;
    private Long userId;
}
