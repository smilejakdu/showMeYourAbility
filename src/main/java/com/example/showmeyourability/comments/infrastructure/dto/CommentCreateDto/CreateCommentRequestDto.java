package com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateCommentRequestDto {
    private String content;

    private Long teacherId;

    private double likes;

    @Builder
    public CreateCommentRequestDto(
            String content,
            Long teacherId,
            double likes
    ) {
        this.content = content;
        this.teacherId = teacherId;
        this.likes = likes;
    }
}
