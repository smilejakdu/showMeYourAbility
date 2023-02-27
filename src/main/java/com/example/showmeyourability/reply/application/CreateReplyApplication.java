package com.example.showmeyourability.reply.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.reply.domain.Reply;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyDto.CreateReplyResponseDto;
import com.example.showmeyourability.reply.infrastructure.repository.ReplyRepository;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateReplyApplication {
    private final ReplyRepository replyRepository;

    @Transactional
    public CreateReplyResponseDto execute(
            String content,
            User user,
            Comments comments
    ) {
        Reply newReply = Reply.builder()
                .content(content)
                .user(user)
                .comments(comments)
                .build();

        Reply reply = replyRepository.save(newReply);
        return CreateReplyResponseDto.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .commentsId(reply.getParentComment().getId())
                .build();
    }
}
