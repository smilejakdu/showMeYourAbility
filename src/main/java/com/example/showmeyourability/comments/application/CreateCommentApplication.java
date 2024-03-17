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
    private final KafkaTemplate<String, String> kafkaTemplate; // KafkaTemplate 주입

    @Transactional
    public CreateCommentResponseDto execute(
            User user,
            CreateCommentRequestDto request
    ) {
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
        String message = convertToKafkaMessage(saved); // 예시 메서드, 실제 메시지 형식에 맞게 변환
        kafkaTemplate.send("commentTopic", message); // "commentTopic"은 실제 사용할 Kafka 토픽 이름으로 변경해야 합니다.

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
        // 실제 메시지 형식은 애플리케이션 요구 사항에 따라 다를 수 있습니다.
        return String.format("New comment by %s: %s", comment.getUser().getName(), comment.getContent());
    }
}
