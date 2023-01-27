package com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long userId;
    private Long teacherId;
    private String content;
    private String createdAt;
    private String updatedAt;
}
