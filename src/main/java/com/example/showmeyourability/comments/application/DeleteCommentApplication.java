package com.example.showmeyourability.comments.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.example.showmeyourability.shared.CoreSuccessResponse.coreSuccessResponse;

@Service
@RequiredArgsConstructor
public class DeleteCommentApplication {
    private final CommentRepository commentRepository;

    @Transactional
    public CoreSuccessResponse execute(
            User user,
            Long commentId
    ) {

        Comments comments = commentRepository.findByIdAndUserId(commentId, user.getId())
                .orElseThrow(() -> new HttpExceptionCustom(
                        false,
                        "요청한 commentId에 해당하는 댓글이 존재하지 않습니다.",
                        HttpStatus.NOT_FOUND
                ));

        comments.setDeletedAt(LocalDateTime.now());
        Comments savedComment = commentRepository.save(comments);
        return coreSuccessResponse(true, savedComment, "댓글 삭제 성공", HttpStatus.OK.value());
    }
}
