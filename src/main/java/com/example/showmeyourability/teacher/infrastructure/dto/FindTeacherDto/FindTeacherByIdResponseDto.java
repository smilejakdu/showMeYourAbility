package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import com.example.showmeyourability.teacher.domain.Teacher;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
public class FindTeacherByIdResponseDto {
    private Teacher teacher;

    @Builder
    public FindTeacherByIdResponseDto(
            Teacher teacher
    ) {
        this.teacher = teacher;
    }
}
