package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto.CommentDto;
import com.example.showmeyourability.teacher.domain.Teacher;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class FindTeacherByIdResponseDto {
    private Teacher teacher;

    private List<CommentDto> commentDtoList;

    @Builder
    public FindTeacherByIdResponseDto(
            Teacher teacher,
            List<CommentDto> commentDtoList
    ) {
        this.teacher = teacher;
        this.commentDtoList = commentDtoList;
    }
}
