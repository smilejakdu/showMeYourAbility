package com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto;

import com.example.showmeyourability.shared.CheckValidity;
import lombok.Data;

@Data
public class CreateCommentRequestDto implements CheckValidity {
    private String content;

    @Override
    public void check() {
        if(content == null || content.isEmpty()) {
            throw new RuntimeException("content is empty");
        }
    }
}
