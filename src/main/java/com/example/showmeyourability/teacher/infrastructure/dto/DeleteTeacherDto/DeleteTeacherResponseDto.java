package com.example.showmeyourability.teacher.infrastructure.dto.DeleteTeacherDto;

import lombok.Builder;
import lombok.Data;

@Data
public class DeleteTeacherResponseDto {

    private Long teacherId;

    @Builder
    public DeleteTeacherResponseDto(Long teacherId) {
        this.teacherId = teacherId;
    }
}
