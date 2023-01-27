package com.example.showmeyourability.comments.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.infrastructure.dto.UpdateCommentDto.UpdateCommentReqeustDto;
import com.example.showmeyourability.comments.infrastructure.dto.UpdateCommentDto.UpdateCommentResponseDto;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateCommentApplication {
    private final CommentRepository commentRepository;
    @Transactional
    public UpdateCommentResponseDto execute(
            Long commentId,
            UpdateCommentReqeustDto request
    ) {
        Comments comments = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("요청한 commentId에 해당하는 댓글이 존재하지 않습니다."));

        comments.setContent(request.getContent());
        Comments updatedComment = commentRepository.save(comments);

        UpdateCommentResponseDto responseDto = new UpdateCommentResponseDto();
        responseDto.toDto(updatedComment);
        return responseDto;
    }
}
