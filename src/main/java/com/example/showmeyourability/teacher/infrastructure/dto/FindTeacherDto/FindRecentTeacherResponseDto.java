package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class FindRecentTeacherResponseDto {
    List<TeacherDto> teacherDtoList;

    @Builder
    public FindRecentTeacherResponseDto(
            List<TeacherDto> teacherDtoList
    ) {
        this.teacherDtoList = teacherDtoList;
    }
}
