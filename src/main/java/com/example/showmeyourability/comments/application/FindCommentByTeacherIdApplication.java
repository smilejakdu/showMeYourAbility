package com.example.showmeyourability.comments.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto.CommentDto;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import com.example.showmeyourability.shared.CoreSuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCommentByTeacherIdApplication {
    private final CommentRepository commentRepository;


    @Transactional
    public CoreSuccessResponse execute(
            Long teacherId
    ) {
        List<Comments> comments = commentRepository.findByTeacherId(teacherId);

        List<CommentDto> commentDtoList = getCommentDtoList(comments);
        return new CoreSuccessResponse(commentDtoList);
    }

    private List<CommentDto> getCommentDtoList(List<Comments> comments) {
        ArrayList<CommentDto> commentDtoList = new ArrayList<>(Collections.emptyList());
        for (Comments comment : comments) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setUserId(comment.getUser().getId());
            commentDto.setTeacherId(comment.getTeacher().getId());
            commentDto.setContent(comment.getContent());
            commentDto.setCreatedAt(String.valueOf(comment.getCreatedAt()));
            commentDto.setUpdatedAt(String.valueOf(comment.getUpdatedAt()));
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }
}
