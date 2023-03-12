package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class FindTeacherResponseDto {
    int lastPage;
    List<TeacherDto> teachers;

    @Builder
    public FindTeacherResponseDto(
            int lastPage,
            List<TeacherDto> teachers
    ) {
        this.lastPage = lastPage;
        this.teachers = teachers;
    }
}

