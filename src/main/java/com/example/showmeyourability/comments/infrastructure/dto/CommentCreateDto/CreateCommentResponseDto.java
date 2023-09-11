package com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentResponseDto {
    private Long commentId;
    private String content;

    private Double likes;

    private Long teacherId;

    private Long userId;

    @Builder
    public CreateCommentResponseDto(
            Long commentId,
            String content,
            Long teacherId,
            Long userId,
            Double likes
    ) {
        this.commentId = commentId;
        this.content = content;
        this.teacherId = teacherId;
        this.userId = userId;
        this.likes = likes;
    }
}
