package com.example.showmeyourability.comments.infrastructure.dto.UpdateCommentDto;

import com.example.showmeyourability.comments.domain.Comments;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCommentResponseDto {
    private Long commentId;
    private String content;
    private Double likes;
    private Long teacherId;
    private Long userId;
}
