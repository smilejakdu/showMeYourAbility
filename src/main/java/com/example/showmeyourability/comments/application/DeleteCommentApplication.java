package com.example.showmeyourability.comments.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import com.example.showmeyourability.shared.CoreSuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCommentApplication {
    private final CommentRepository commentRepository;

    @Transactional
    public CoreSuccessResponse execute(
            Long commentId
    ) {
        Comments comments = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("요청한 commentId에 해당하는 댓글이 존재하지 않습니다."));

        commentRepository.delete(comments);
        return new CoreSuccessResponse(commentId);
    }
}
