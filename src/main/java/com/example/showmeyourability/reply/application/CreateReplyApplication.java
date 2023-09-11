package com.example.showmeyourability.reply.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import com.example.showmeyourability.reply.domain.Reply;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyRequestDto;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyResponseDto;
import com.example.showmeyourability.reply.infrastructure.repository.ReplyRepository;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateReplyApplication {
    private final ReplyRepository replyRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentsRepository;

    @Transactional
    public CreateReplyResponseDto execute(
            User user,
            CreateReplyRequestDto requestDto
    ) {
        Comments comment = commentsRepository.findById(requestDto.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));

        Reply reply = Reply.builder()
                .comments(comment)
                .user(user)
                .content(requestDto.getContent())
                .build();
        Reply savedReply = replyRepository.save(reply);
        return CreateReplyResponseDto
                .builder()
                .userId(savedReply.getUser().getId())
                .email(savedReply.getUser().getEmail())
                .commentId(savedReply.getComments().getId())
                .replyId(savedReply.getId())
                .content(savedReply.getContent())
                .build();
    };

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
                .replyId(reply.getId())
                .content(reply.getContent())
                .commentId(reply.getComments().getId())
                .build();
    }
}
