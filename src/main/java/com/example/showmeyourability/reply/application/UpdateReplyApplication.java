package com.example.showmeyourability.reply.application;

import com.example.showmeyourability.reply.domain.Reply;
import com.example.showmeyourability.reply.infrastructure.dto.UpdateReplyRequestDto;
import com.example.showmeyourability.reply.infrastructure.dto.UpdateReplyResponseDto;
import com.example.showmeyourability.reply.infrastructure.repository.ReplyRepository;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UpdateReplyApplication {
    private final ReplyRepository replyRepository;

    @Transactional
    public UpdateReplyResponseDto execute(
            User user,
            Long replyId,
            String content
    ) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        reply.setContent(content);
        Reply savedReply = replyRepository.save(reply);
        return UpdateReplyResponseDto
                .builder()
                .replyId(savedReply.getId())
                .content(savedReply.getContent())
                .build();
    }
}
