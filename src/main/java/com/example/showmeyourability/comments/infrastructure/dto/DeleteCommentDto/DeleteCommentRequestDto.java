package com.example.showmeyourability.comments.infrastructure.dto.DeleteCommentDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeleteCommentRequestDto {
    @Schema(description = "댓글 id", example = "1")
    Long id;
}
