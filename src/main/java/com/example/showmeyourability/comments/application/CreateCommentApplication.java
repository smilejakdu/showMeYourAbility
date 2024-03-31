package com.example.showmeyourability.comments.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentRequestDto;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentResponseDto;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCommentApplication {
    private final CommentRepository commentRepository;
    private final TeacherRepository teacherRepository;
    private final RedisTemplate<String, Object> redisTemplate; // RedisTemplate 추가

    @Transactional
    public CreateCommentResponseDto execute(User user, CreateCommentRequestDto request) {
        Teacher foundTeacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new HttpExceptionCustom(
                        false,
                        "해당 선생님을 찾을 수 없습니다.",
                        HttpStatus.NOT_FOUND
                ));

        Comments newComments = Comments.of(user, foundTeacher, request.getContent(), request.getLikes());
        Comments saved = commentRepository.save(newComments);

        // 댓글과 관련된 Redis 캐시 초기화 로직 추가
        invalidateRelatedCache(saved.getTeacher().getId());

        return CreateCommentResponseDto.builder()
                .userId(saved.getUser().getId())
                .teacherId(saved.getTeacher().getId())
                .likes(saved.getLikes()) // LikesVo로 변경됐다면 getValue() 사용
                .content(saved.getContent()) // ContentVo로 변경됐다면 getValue() 사용
                .build();
    }

    // 관련 캐시 초기화 메소드
    private void invalidateRelatedCache(Long teacherId) {
        String cacheKey = "comments:teacher:" + teacherId; // 댓글과 관련된 캐시 키 패턴
        redisTemplate.delete(cacheKey);
        // 필요한 경우 여러 관련 캐시 키에 대해 삭제 작업 수행
    }
}
