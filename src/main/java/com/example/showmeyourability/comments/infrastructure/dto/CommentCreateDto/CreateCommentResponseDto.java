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
    private Long teacherId;
    private Long userId;
}
