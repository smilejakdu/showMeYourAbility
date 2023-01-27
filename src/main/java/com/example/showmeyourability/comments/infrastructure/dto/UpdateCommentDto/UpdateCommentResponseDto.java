package com.example.showmeyourability.comments.infrastructure.dto.UpdateCommentDto;

import com.example.showmeyourability.comments.domain.Comments;
import lombok.Data;

@Data
public class UpdateCommentResponseDto {
    private Long commentId;

    private String content;

    private Long teacherId;

    private Long userId;

    public UpdateCommentResponseDto toDto(Comments comments) {
        UpdateCommentResponseDto responseDto = new UpdateCommentResponseDto();
        responseDto.setCommentId(comments.getId());
        responseDto.setContent(comments.getContent());
        responseDto.setTeacherId(comments.getTeacher().getId());
        responseDto.setUserId(comments.getUser().getId());
        return responseDto;
    }
}
