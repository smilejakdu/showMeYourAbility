package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import lombok.Data;

import java.util.List;

@Data
public class FindTeacherResponseDto {
    int lastPage;
    List<TeacherDto> teachers;
}

