package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindRecentTeacherResponseDto {
    List<TeacherDto> teacherDtoList;
}
