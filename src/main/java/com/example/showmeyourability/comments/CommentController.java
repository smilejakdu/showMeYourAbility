package com.example.showmeyourability.comments;

import com.example.showmeyourability.comments.application.CreateCommentApplication;
import com.example.showmeyourability.comments.application.FindCommentByTeacherIdApplication;
import com.example.showmeyourability.comments.application.UpdateCommentApplication;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentRequestDto;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentResponseDto;
import com.example.showmeyourability.comments.infrastructure.dto.UpdateCommentDto.UpdateCommentReqeustDto;
import com.example.showmeyourability.comments.infrastructure.dto.UpdateCommentDto.UpdateCommentResponseDto;
import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.shared.SecurityService;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CreateCommentApplication createCommentApplication;
    private final UpdateCommentApplication updateCommentApplication;
    private final FindCommentByTeacherIdApplication findCommentByTeacherIdApplication;
    private final SecurityService securityService;
    private final UserRepository userRepository;

    @PostMapping("/")
    CreateCommentResponseDto createComment(
            @RequestBody CreateCommentRequestDto request,
            @RequestHeader("access-token") String token
    ) {
        System.out.println("token: " + token);
        User responseUser = securityService.getSubject(token);
        return createCommentApplication.execute(responseUser,request);
    }

    @PutMapping("/{commentId}")
    UpdateCommentResponseDto updateComment(
            @PathVariable("commentId") Long commentId,
            @RequestBody UpdateCommentReqeustDto request,
            @RequestHeader("access-token") String token
    ) {
        System.out.println("token: " + token);
        securityService.getSubject(token);
        return updateCommentApplication.execute(commentId, request);
    }
    @GetMapping("/{teacherId}")
    CoreSuccessResponse findComment(
            @PathVariable("teacherId") Long teacherId
    ) {
        return findCommentByTeacherIdApplication.execute(teacherId);
    }

    @DeleteMapping("/{commentId}")
    CoreSuccessResponse deleteComment(
            @PathVariable("commentId") Long commentId
    ) {
        return findCommentByTeacherIdApplication.execute(commentId);
    }
}
