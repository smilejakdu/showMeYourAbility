package com.example.showmeyourability.comments.infrastructure.dto.UpdateCommentDto;

import com.example.showmeyourability.teacher.domain.Teacher;
import lombok.Data;

@Data
public class UpdateCommentReqeustDto {

    private Long commnetId;

    private String content;

    private Teacher teacher;
}
