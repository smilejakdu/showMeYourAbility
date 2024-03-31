package com.example.showmeyourability.comments.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.domain.LikesVo;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentRequestDto;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentResponseDto;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCommentApplication {
    private final CommentRepository commentRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public CreateCommentResponseDto execute(User user, CreateCommentRequestDto request) {
        Teacher foundTeacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new HttpExceptionCustom(
                        false,
                        "해당 선생님을 찾을 수 없습니다.",
                       HttpStatus.NOT_FOUND
                ));

        LikesVo responseLikes = LikesVo.of(request.getLikes());
        System.out.println("Likes: " + responseLikes.getValue());

        Comments newComments = Comments.of(user, foundTeacher, request.getContent(), request.getLikes());
        Comments saved = commentRepository.save(newComments);

        return CreateCommentResponseDto
                .builder()
                .userId(saved.getUser().getId())
                .teacherId(saved.getTeacher().getId())
                .likes(saved.getLikes())
                .content(saved.getContent())
                .build();
    }
}