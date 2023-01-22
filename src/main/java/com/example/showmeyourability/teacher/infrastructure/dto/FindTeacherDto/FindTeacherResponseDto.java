package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import com.example.showmeyourability.teacher.domain.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class FindTeacherResponseDto {
    int lastPage;
    List<Teacher> teachers;
}
