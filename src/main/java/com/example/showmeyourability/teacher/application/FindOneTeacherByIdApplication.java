package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.comments.domain.QComments;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.shared.Service.redisService.RedisService;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByIdResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.TeacherDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindOneTeacherByIdApplication {
    private final JPAQueryFactory queryFactory; // JPAQueryFactory 주입
    private final QTeacher qTeacher = QTeacher.teacher;
    private final QComments qComments = QComments.comments;
    private final RedisService redisService;
    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public FindTeacherByIdResponseDto execute(Long teacherId) {
        Teacher teacher = queryFactory
                .selectFrom(QTeacher.teacher)
                .where(QTeacher.teacher.id.eq(teacherId))
                .leftJoin(QTeacher.teacher.comments, QComments.comments)
                .fetchOne();

        if (teacher == null) {
            throw new HttpExceptionCustom(
                    false,
                    "해당하는 선생님을 찾을 수 없습니다.",
                    HttpStatus.NOT_FOUND
            );
        }


        Double avgLikes = queryFactory
                .select(qComments.likes.avg().coalesce(0.0))
                .from(QComments.comments)
                .where(QComments.comments.teacher.id.eq(teacherId))
                .fetchOne();
        TeacherDto teacherDto = getTeacherDto(avgLikes, teacher);

        return FindTeacherByIdResponseDto.builder()
                .teacher(teacherDto)
                .commentDtoList(teacher.getComments())
                .build();
    }

    private TeacherDto getTeacherDto(Double avgLikes, Teacher teacher) {
        Double avgScore = Double.parseDouble(String.format("%.2f", avgLikes));

        return new TeacherDto(
                teacher.getId(),
                teacher.getCareer(),
                teacher.getUser().getEmail(), // 여기에서 선생님과 연관된 사용자의 이메일을 포함합니다.
                teacher.getUser().getId(), // 이는 예시로, 실제 구조에 따라 달라질 수 있습니다.
                teacher.getSkill(),
                avgScore, // 평균 점수 등 추가 필드에 해당하는 값이 필요할 수 있습니다.
                teacher.getCreatedAt()
        );
    }

}
