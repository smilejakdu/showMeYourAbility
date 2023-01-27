package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.shared.HttpException;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByIdResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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

    @Transactional
    public FindTeacherByIdResponseDto findOneTeacherById(
            Long teacherId
    ) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new HttpException("해당하는 선생님을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));


        System.out.println("teacher = " + teacher);
        FindTeacherByIdResponseDto responseDto = new FindTeacherByIdResponseDto();
        BeanUtils.copyProperties(teacher,
                responseDto,
                "user",
                "comments"
        );
        return responseDto;
    }
}
