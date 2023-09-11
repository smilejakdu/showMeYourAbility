package com.example.showmeyourability.reply.infrastructure.dto.DeleteReplyDto;

import lombok.Data;

@Data
public class DeleteReplyResponseDto {
    private Long id;

    private Long commentsId;

    private Long userId;

    private String content;
}
