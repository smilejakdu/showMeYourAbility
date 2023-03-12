package com.example.showmeyourability.reply.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateReplyRequestDto {
    private Long replyId;
    private String content;

    @Builder
    public UpdateReplyRequestDto(
            Long replyId,
            String content
    ) {
        this.replyId = replyId;
        this.content = content;
    }
}
