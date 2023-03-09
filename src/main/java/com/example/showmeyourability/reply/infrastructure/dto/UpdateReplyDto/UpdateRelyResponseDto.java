package com.example.showmeyourability.reply.infrastructure.dto.UpdateReplyDto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateRelyResponseDto {
    private Long id;

    private Long commentsId;

    private String content;

    @Builder
    public UpdateRelyResponseDto(
            Long id,
            Long commentsId,
            String content
    ) {
        this.id = id;
        this.commentsId = commentsId;
        this.content = content;
    }
}
