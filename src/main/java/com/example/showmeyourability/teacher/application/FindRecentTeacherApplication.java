package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindRecentTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.TeacherDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindRecentTeacherApplication {
    private final JPAQueryFactory queryFactory;

    @Transactional
    public FindRecentTeacherResponseDto execute() {
        // 4일 전
        LocalDateTime fourDaysAgo = LocalDateTime.now().minusDays(4);
        QTeacher qTeacher = QTeacher.teacher;

        List<TeacherDto> teacherDtoList = queryFactory.selectFrom(qTeacher)
                .where(qTeacher.createdAt.after(fourDaysAgo))
                .innerJoin(qTeacher.user)
                .fetch()
                .stream()
                .map(teacher -> TeacherDto.builder()
                        .id(teacher.getId())
                        .career(teacher.getCareer())
                        .email(teacher.getUser().getEmail())
                        .skill(teacher.getSkill())
                        .createdAt(teacher.getCreatedAt())
                        .userId(teacher.getUser().getId())
                        .build())
                .collect(Collectors.toList());
        System.out.println("teacherDtoList = " + teacherDtoList);
        return new FindRecentTeacherResponseDto(teacherDtoList);
    }
}