package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByIdResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public CoreSuccessResponse findOneTeacherById(
            Long teacherId
    ) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("해당 선생님은 존재하지 않습니다."));
//       왜 이게 500 으로 뜨는거지 ??
        System.out.println("teacher = " + teacher);
        FindTeacherByIdResponseDto responseDto = new FindTeacherByIdResponseDto();
        BeanUtils.copyProperties(teacher,
                responseDto,
                "user",
                "comments"
        );
        return new CoreSuccessResponse(responseDto);
    }
}
