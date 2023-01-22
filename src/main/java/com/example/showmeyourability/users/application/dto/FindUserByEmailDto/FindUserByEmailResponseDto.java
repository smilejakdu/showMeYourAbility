package com.example.showmeyourability.users.application.dto.FindUserByEmailDto;

import com.example.showmeyourability.comments.domain.Comments;
import lombok.Data;

import java.util.List;

@Data
public class FindUserByEmailResponseDto {
    private String email;

    private List<Comments> comments;
}
