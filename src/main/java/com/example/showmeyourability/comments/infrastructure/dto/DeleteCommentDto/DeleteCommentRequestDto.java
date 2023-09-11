package com.example.showmeyourability.comments.infrastructure.dto.DeleteCommentDto;

import com.example.showmeyourability.shared.CheckValidity;
import lombok.Data;

@Data
public class DeleteCommentRequestDto implements CheckValidity {
    Long id;

    @Override
    public void check() {
        if(id == null) {
            throw new RuntimeException("id is empty");
        }
    }
}
