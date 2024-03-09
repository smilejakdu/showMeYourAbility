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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.showmeyourability.shared.CoreSuccessResponse.coreSuccessResponse;

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
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "댓글 작성하기",
            description = "댓글 작성하기"
    )
    public CreateCommentResponseDto createComment(
            HttpServletRequest httpServletRequest,
            @RequestBody CreateCommentRequestDto createCommentRequestDto
    ) {
        Cookie[] cookies = httpServletRequest.getCookies();
        User responseUser = securityService.getTokenByCookie(cookies);
        return createCommentApplication.execute(responseUser, createCommentRequestDto);
    }

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "댓글 수정하기",
            description = "댓글 수정하기"
    )
    public UpdateCommentResponseDto updateComment(
            HttpServletRequest httpServletRequest,
            @PathVariable("commentId") Long commentId,
            @RequestBody UpdateCommentReqeustDto request
    ) {
        Cookie[] cookies = httpServletRequest.getCookies();
        securityService.getTokenByCookie(cookies);
        return updateCommentApplication.execute(commentId, request);
    }

    @GetMapping("/teacher")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "해당하는 선생님 댓글 불러오기",
            description = "해당하는 선생님 댓글 불러오기"
    )
    public CoreSuccessResponse findComment(
            @RequestParam() Long teacherId
    ) {
        return findCommentByTeacherIdApplication.execute(teacherId);
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "댓글 하나 불러오기",
            description = "댓글 하나 불러오기"
    )
    public CoreSuccessResponse findCommentAndReply(
            @PathVariable("commentId") Long commentId
    ) {
        FindCommentAndReplyResponseDto findCommentAndReplyResponseDto = findCommentAndReplyApplication.execute(commentId);
        return coreSuccessResponse(true, findCommentAndReplyResponseDto, "댓글 불러오기 성공", HttpStatus.OK.value());
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "댓글 삭제하기",
            description = "댓글 삭제하기"
    )
    public CoreSuccessResponse deleteComment(
            HttpServletRequest httpServletRequest,
            @PathVariable("commentId") Long commentId
    ) {
        Cookie[] cookies = httpServletRequest.getCookies();
        User responseUser = securityService.getTokenByCookie(cookies);
        return deleteCommentApplication.execute(responseUser, commentId);
    }
}
