package com.example.showmeyourability.reply.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import com.example.showmeyourability.reply.domain.Reply;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyRequestDto;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyResponseDto;
import com.example.showmeyourability.reply.infrastructure.repository.ReplyRepository;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateReplyApplication {
    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateReplyResponseDto execute(
            User user,
            CreateReplyRequestDto requestDto
    ) {
        Comments comment = findCommentById(requestDto.getCommentId());
        Reply reply = buildReply(user, requestDto.getContent(), comment);
        Reply savedReply = replyRepository.save(reply);

        return buildCreateReplyResponseDto(savedReply);
    }

    private Comments findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));
    }

    private Reply buildReply(User user, String content, Comments comment) {
        return Reply.builder()
                .comments(comment)
                .user(user)
                .content(content)
                .build();
    }

    private CreateReplyResponseDto buildCreateReplyResponseDto(Reply savedReply) {
        return CreateReplyResponseDto
                .builder()
                .userId(savedReply.getUser().getId())
                .email(savedReply.getUser().getEmail())
                .commentId(savedReply.getComments().getId())
                .replyId(savedReply.getId())
                .content(savedReply.getContent())
                .build();
    }
}
