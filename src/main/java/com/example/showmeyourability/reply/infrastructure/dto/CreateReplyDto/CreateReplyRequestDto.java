package com.example.showmeyourability.reply.infrastructure.dto.CreateReplyDto;

import com.example.showmeyourability.comments.domain.Comments;
import lombok.Data;

@Data
public class CreateReplyRequestDto {
    private Comments comments;
    private String content;
}
