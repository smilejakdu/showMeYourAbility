package com.example.showmeyourability.reply.infrastructure.dto.CreateReplyDto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateReplyResponseDto {
    private Long id;

    private Long commentsId;

    private String content;

    @Builder
    public CreateReplyResponseDto(
            Long id,
            Long commentsId,
            String content
    ) {
        this.id = id;
        this.commentsId = commentsId;
        this.content = content;
    }
}
