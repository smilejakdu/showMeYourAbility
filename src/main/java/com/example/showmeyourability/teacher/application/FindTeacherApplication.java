package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTeacherApplication {
    private final TeacherRepository teacherRepository;

    @Transactional
    public FindTeacherResponseDto findAllTeacher(
            int page,
            int size
    ) {
        List<Teacher> teachers = teacherRepository.findAll(PageRequest.of(page, size)).getContent();
        int lastPage = teacherRepository.findAll(PageRequest.of(page, size)).getTotalPages();

        FindTeacherResponseDto dto = new FindTeacherResponseDto();
        dto.setLastPage(lastPage);
        dto.setTeachers(teachers);
        return dto;
    }
}
