package com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto;

import lombok.Builder;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long userId;
    private Long teacherId;
    private Double likes;
    private String content;
    private String createdAt;
    private String updatedAt;

    @Builder
    public CommentDto(
            Long id,
            Long userId,
            Long teacherId,
            Double likes,
            String content,
            String createdAt,
            String updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.teacherId = teacherId;
        this.likes = likes;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
