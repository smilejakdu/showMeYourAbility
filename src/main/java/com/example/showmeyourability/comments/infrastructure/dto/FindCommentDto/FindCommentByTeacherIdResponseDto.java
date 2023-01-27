package com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto;

import lombok.Data;

import java.util.Collections;
import java.util.List;



@Data
public class FindCommentByTeacherIdResponseDto {
//    List<Comments> comments; 이렇게 return 하면 에러발생
    List<CommentDto> comments = Collections.emptyList();
}
