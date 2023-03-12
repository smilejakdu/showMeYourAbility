package com.example.showmeyourability.comments.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentRequestDto;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentResponseDto;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCommentApplication {
    private final CommentRepository commentRepository;

    private final TeacherRepository teacherRepository;

    @Transactional
    public CreateCommentResponseDto execute(
            User user,
            CreateCommentRequestDto request
    ) {
        Teacher foundTeacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("해당 선생님을 찾을 수 없습니다."));

         Comments newComments = Comments
                 .builder()
                 .user(user)
                 .teacher(foundTeacher)
                 .likes(request.getLikes())
                 .content(request.getContent())
                 .build();

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
