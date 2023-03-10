package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.shared.Exception.HttpException;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByIdResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.TeacherDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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

        HashMap<Integer, Double> hashMap = new HashMap<>();
        for (Teacher teacher : teachers) {
            Double sum = 0.0;
            List<Comments> comments = teacher.getComments();
            if (comments.isEmpty()) {
                hashMap.put(teacher.getId().intValue(), 0.0);
                continue;
            }

            for (Comments comment : comments) {
                sum += comment.getLikes();
            }
            hashMap.put(teacher.getId().intValue(), sum / comments.size());
        }

        List<TeacherDto> teacherDtos = new ArrayList<>();
        for (Teacher teacher : teachers) {
            TeacherDto teacherDto = TeacherDto.builder()
                    .id(teacher.getId())
                    .career(teacher.getCareer())
                    .skill(teacher.getSkill())
                    .userId(teacher.getUser().getId())
                    .avgScore(hashMap.get(teacher.getId().intValue()))
                    .build();
            teacherDtos.add(teacherDto);
        }

        int lastPage = teacherRepository.findAll(PageRequest.of(page, size)).getTotalPages();
        return convertToFindTeacherResponseDto(lastPage, teacherDtos);
    }

    private FindTeacherResponseDto convertToFindTeacherResponseDto(
            int lastPage,
            List<TeacherDto> teachers
    ) {
        return FindTeacherResponseDto.builder()
                .lastPage(lastPage)
                .teachers(teachers)
                .build();
    }

    @Transactional
    public FindTeacherByIdResponseDto findOneTeacherById(
            Long teacherId
    ) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new HttpException("???????????? ???????????? ?????? ??? ????????????.", HttpStatus.NOT_FOUND));

        FindTeacherByIdResponseDto responseDto = new FindTeacherByIdResponseDto();
        BeanUtils.copyProperties(teacher,
                responseDto,
                "user",
                "comments"
        );
        return responseDto;
    }
}
