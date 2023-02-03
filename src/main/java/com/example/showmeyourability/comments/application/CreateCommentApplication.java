package com.example.showmeyourability.comments.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentRequestDto;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentResponseDto;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.users.application.FindUserByEmailApplication;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.FindUserDto.FindUserByEmailResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateCommentApplication {
    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    @Transactional
    public CreateCommentResponseDto execute(
            User user,
            CreateCommentRequestDto request
    ) {
        System.out.println(user.getEmail());

        Comments newComments = new Comments();
        newComments.setUser(user);
        newComments.setContent(request.getContent());

        Comments saved = commentRepository.save(newComments);

        CreateCommentResponseDto responseDto = new CreateCommentResponseDto();

        responseDto.setContent(saved.getContent());
        return responseDto;
    }
}
