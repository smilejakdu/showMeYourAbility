package com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto;

import com.example.showmeyourability.comments.domain.Comments;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class FindCommentAndReplyResponseDto {
    private Comments comments;

    private List<ReplyDto> replyeList;

    @Builder
    public FindCommentAndReplyResponseDto(
            Comments comments,
            List<ReplyDto> replyeList
    ) {
        this.comments = comments;
        this.replyeList = replyeList;
    }
}
