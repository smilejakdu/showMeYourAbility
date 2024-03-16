package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import com.example.showmeyourability.comments.domain.Comments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class FindTeacherByIdResponseDto {
    private TeacherDto teacher;
    private List<Comments> commentDtoList;
}
