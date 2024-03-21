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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindOneTeacherByIdApplication {
    private final static String FIND_ONE_TEACHER_CACHE_KEY = "findOneTeacher";
    private final JPAQueryFactory queryFactory;
    private final QTeacher qTeacher = QTeacher.teacher;
    private final QComments qComments = QComments.comments;
    private final RedisService redisService;


    private Optional<FindTeacherByIdResponseDto> getTeacherFromCache(Long teacherId) {
        String cacheValue = redisService.getValue(FIND_ONE_TEACHER_CACHE_KEY + teacherId);
        if (cacheValue != null && !cacheValue.isEmpty()) {
            try {
                return Optional.of(new ObjectMapper().readValue(cacheValue, FindTeacherByIdResponseDto.class));
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse JSON from cache", e);
            }
        }
        return Optional.empty();
    }

    private FindTeacherByIdResponseDto getTeacherFromDatabase(Long teacherId) {
        Teacher teacher = queryFactory
                .selectFrom(qTeacher)
                .where(qTeacher.id.eq(teacherId))
                .leftJoin(qTeacher.comments, qComments)
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
        FindTeacherByIdResponseDto responseDto = FindTeacherByIdResponseDto.builder()
                .teacher(teacherDto)
                .commentDtoList(teacher.getComments())
                .build();
        saveToCache(responseDto);
        return responseDto;
    }

    private void saveToCache(FindTeacherByIdResponseDto returnValue) {
        try {
            String json = new ObjectMapper().writeValueAsString(returnValue);
            redisService.setValue(FIND_ONE_TEACHER_CACHE_KEY, json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save to cache", e);
        }
    }

    @Transactional(readOnly = true)
    public FindTeacherByIdResponseDto execute(Long teacherId) {
        return getTeacherFromCache(teacherId).orElseGet(() -> getTeacherFromDatabase(teacherId));
    }

    private TeacherDto getTeacherDto(Double avgLikes, Teacher teacher) {
        Double avgScore = Double.parseDouble(String.format("%.2f", avgLikes));

        return new TeacherDto(
                teacher.getId(),
                teacher.getCareer(),
                teacher.getUser().getEmail(),
                teacher.getUser().getId(),
                teacher.getSkill(),
                avgScore,
                teacher.getCreatedAt()
        );
    }

}