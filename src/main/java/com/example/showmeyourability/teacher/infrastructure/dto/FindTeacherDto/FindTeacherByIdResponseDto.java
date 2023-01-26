package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import com.example.showmeyourability.teacher.domain.Teacher;
import lombok.Data;

@Data
public class FindTeacherByIdResponseDto {
    private Teacher teacher;
}
