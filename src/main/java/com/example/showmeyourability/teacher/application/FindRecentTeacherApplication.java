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
        LocalDateTime fourDaysAgo = LocalDateTime.now().minusDays(4);
        QTeacher qTeacher = QTeacher.teacher;

        List<TeacherDto> teacherDtoList = queryFactory.selectFrom(qTeacher)
                .where(qTeacher.createdAt.after(fourDaysAgo))
                .fetch()
                .stream()
                .map(teacher -> TeacherDto.builder()
                        .id(teacher.getId())
                        .career(teacher.getCareer())
                        .email(teacher.getUser().getEmail())
                        .skill(teacher.getSkill())
                        .userId(teacher.getUser().getId())
                        .build())
                .collect(Collectors.toList());

        return FindRecentTeacherResponseDto.builder()
                .teacherDtoList(teacherDtoList)
                .build();
    }
}