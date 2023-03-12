package com.example.showmeyourability.comments.application;


import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto.FindCommentAndReplyResponseDto;
import com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto.ReplyDto;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import com.example.showmeyourability.reply.domain.Reply;
import com.example.showmeyourability.reply.infrastructure.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCommentAndReplyApplication {

    private final CommentRepository commentRepository;

    private final ReplyRepository replyRepository;

    @Transactional
    public FindCommentAndReplyResponseDto execute(Long commentId) {
        Comments foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));

        List<ReplyDto> foundReplyList = getReplyList(foundComment);

        return FindCommentAndReplyResponseDto
                .builder()
                .comments(foundComment)
                .replyeList(foundReplyList)
                .build();
    }

    private List<ReplyDto> getReplyList(Comments foundComment) {
        List<Reply> foundReplyList = replyRepository.findAllByComments(foundComment);
        List<ReplyDto> replyDtoList = new ArrayList<>(Collections.emptyList());
        for (Reply reply : foundReplyList) {
            ReplyDto replyDto = ReplyDto.builder()
                    .replyId(reply.getId())
                    .userId(reply.getUser().getId())
                    .email(reply.getUser().getEmail())
                    .content(reply.getContent())
                    .createdAt(String.valueOf(reply.getCreatedAt()))
                    .build();
            replyDtoList.add(replyDto);
        }
        return replyDtoList;
    }
}
