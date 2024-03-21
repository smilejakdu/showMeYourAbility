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
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCommentApplication {
    private final CommentRepository commentRepository;
    private final TeacherRepository teacherRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String KAFKA_TOPIC = "commentTopic";

    @Transactional
    public CreateCommentResponseDto execute(User user, CreateCommentRequestDto request) {
        Teacher foundTeacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new HttpExceptionCustom(
                        false,
                        "해당 선생님을 찾을 수 없습니다.",
                       HttpStatus.NOT_FOUND
                ));

         Comments newComments = Comments
                 .builder()
                 .user(user)
                 .teacher(foundTeacher)
                 .likes(request.getLikes())
                 .content(request.getContent())
                 .build();

        Comments saved = commentRepository.save(newComments);

        // Kafka로 댓글 생성 이벤트 전송
        String message = convertToKafkaMessage(saved);
        kafkaTemplate.send(KAFKA_TOPIC, message);

        return CreateCommentResponseDto
                .builder()
                .userId(saved.getUser().getId())
                .teacherId(saved.getTeacher().getId())
                .likes(saved.getLikes())
                .content(saved.getContent())
                .build();
    }

    private String convertToKafkaMessage(Comments comment) {
        // 댓글 정보를 Kafka 메시지 형식으로 변환
        return String.format("New comment by %s: %s", comment.getUser().getName(), comment.getContent());
    }
}