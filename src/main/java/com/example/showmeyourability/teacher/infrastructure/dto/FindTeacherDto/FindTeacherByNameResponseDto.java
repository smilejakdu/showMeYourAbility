package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.teacher.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindTeacherByNameResponseDto {
    private Teacher teacher;
    private List<Comments> commentDtoList;
}
