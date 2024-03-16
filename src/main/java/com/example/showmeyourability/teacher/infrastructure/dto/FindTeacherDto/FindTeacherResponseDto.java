package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FindTeacherResponseDto {
    int lastPage;
    List<TeacherDto> teachers;
}
