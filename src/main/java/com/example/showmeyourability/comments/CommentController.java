package com.example.showmeyourability.comments;

import com.example.showmeyourability.comments.application.*;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentRequestDto;
import com.example.showmeyourability.comments.infrastructure.dto.CommentCreateDto.CreateCommentResponseDto;
import com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto.FindCommentAndReplyResponseDto;
import com.example.showmeyourability.comments.infrastructure.dto.UpdateCommentDto.UpdateCommentReqeustDto;
import com.example.showmeyourability.comments.infrastructure.dto.UpdateCommentDto.UpdateCommentResponseDto;
import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.shared.Service.SecurityService;
import com.example.showmeyourability.users.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "comment", description = "댓글 API")
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final UpdateCommentApplication updateCommentApplication;
    private final DeleteCommentApplication deleteCommentApplication;
    private final FindCommentByTeacherIdApplication findCommentByTeacherIdApplication;
    private final FindCommentAndReplyApplication findCommentAndReplyApplication;
    private final CreateCommentApplication createCommentApplication;
    private final SecurityService securityService;

    @PostMapping()
    CreateCommentResponseDto createComment(
            @RequestBody CreateCommentRequestDto request,
            @RequestHeader("access-token") String token
    ) {
        User responseUser = securityService.getSubject(token);
        return createCommentApplication.execute(responseUser,request);
    }

    @PutMapping("/{commentId}")
    UpdateCommentResponseDto updateComment(
            @PathVariable("commentId") Long commentId,
            @CookieValue("access-token") String token,
            @RequestBody UpdateCommentReqeustDto request
    ) {
        System.out.println("token: " + token);
        securityService.getSubject(token);
        return updateCommentApplication.execute(commentId, request);
    }

    @GetMapping("/teacher")
    CoreSuccessResponse findComment(
            @RequestParam() Long teacherId
    ) {
        return findCommentByTeacherIdApplication.execute(teacherId);
    }

    @GetMapping("/{commentId}")
    FindCommentAndReplyResponseDto findCommentAndReply(
            @PathVariable("commentId") Long commentId
    ) {
        return findCommentAndReplyApplication.execute(commentId);
    }

    @DeleteMapping("/{commentId}")
    CoreSuccessResponse deleteComment(
            @CookieValue("access-token") String token,
            @PathVariable("commentId") Long commentId
    ) {
        User user = securityService.getSubject(token);
        return deleteCommentApplication.execute(user, commentId);
    }
}
