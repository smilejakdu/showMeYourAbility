package com.example.showmeyourability.reply.application;

import com.example.showmeyourability.reply.domain.Reply;
import com.example.showmeyourability.reply.infrastructure.dto.UpdateReplyDto.UpdateRelyResponseDto;
import com.example.showmeyourability.reply.infrastructure.repository.ReplyRepository;
import com.example.showmeyourability.shared.Exception.HttpException;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateReplyApplication {
    private final ReplyRepository replyRepository;

    @Transactional
    public UpdateRelyResponseDto execute(
            Long id,
            String content,
            User user
    ) {
        Reply foundReply = replyRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new HttpException("해당 댓글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        foundReply.setContent(content);

        Reply reply = replyRepository.save(foundReply);
        return UpdateRelyResponseDto.builder()
                .id(reply.getId())
                .commentsId(reply.getParentComment().getId())
                .content(reply.getContent())
                .build();
    }
}
