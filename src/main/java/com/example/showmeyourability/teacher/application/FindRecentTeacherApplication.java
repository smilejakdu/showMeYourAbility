package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindRecentTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.TeacherDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindRecentTeacherApplication {
    private final TeacherRepository teacherRepository;

    @Transactional
    public FindRecentTeacherResponseDto execute() {
        LocalDateTime fourDaysAgo = LocalDateTime.now().minusDays(4);
        List<Teacher> teachers = teacherRepository.findByCreatedAtAfter(fourDaysAgo)
                .stream().toList();

        List<TeacherDto> teacherDtoList = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherDtoList.add(TeacherDto.builder()
                    .id(teacher.getId())
                    .career(teacher.getCareer())
                    .email(teacher.getUser().getEmail())
                    .skill(teacher.getSkill())
                    .userId(teacher.getUser().getId())
                    .build());
        }

        return FindRecentTeacherResponseDto.builder()
                .teacherDtoList(teacherDtoList)
                .build();
    }
}
