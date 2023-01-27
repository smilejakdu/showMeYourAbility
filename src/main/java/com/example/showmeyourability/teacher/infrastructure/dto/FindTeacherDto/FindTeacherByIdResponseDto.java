package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import com.example.showmeyourability.teacher.domain.Teacher;
import lombok.Data;

import java.util.Optional;

@Data
public class FindTeacherByIdResponseDto {
    private Optional<Teacher> teacher;
}
