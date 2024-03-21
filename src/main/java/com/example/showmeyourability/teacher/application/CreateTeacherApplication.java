package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.CreateTeacherDto.CreateTeacherRequestDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Service
@RequiredArgsConstructor
public class CreateTeacherApplication {

    private final TeacherRepository teacherRepository;
    private final RedisTemplate<String, CreateTeacherRequestDto> redisTemplate;
    private final JPAQueryFactory queryFactory; // JPAQueryFactory 주입

    private final QTeacher qTeacher = QTeacher.teacher;


    @Transactional
    public void execute(
            Long userId,
            @RequestBody CreateTeacherRequestDto createTeacherRequestDto
    ) {
        // Save to DB
        // ...

        // Cache new data in Redis
        ValueOperations<String, CreateTeacherRequestDto> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("teacher:" + userId, createTeacherRequestDto);
    }
}

