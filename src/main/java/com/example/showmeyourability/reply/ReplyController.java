package com.example.showmeyourability.reply;

import com.example.showmeyourability.reply.application.CreateReplyApplication;
import com.example.showmeyourability.reply.application.UpdateReplyApplication;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyRequestDto;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyResponseDto;
import com.example.showmeyourability.reply.infrastructure.dto.UpdateReplyResponseDto;
import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.users.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.showmeyourability.shared.Service.SecurityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.showmeyourability.shared.CoreSuccessResponse.coreSuccessResponse;

@RestController
@Tag(name = "reply", description = "대댓글 API")
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final CreateReplyApplication createReplyApplication;
    private final UpdateReplyApplication updateReplyApplication;
    private final SecurityService securityService;

    @PostMapping()
    public CoreSuccessResponse createReply(
            HttpServletRequest httpServletRequest,
            @RequestBody CreateReplyRequestDto requestDto
    ) {
        Cookie[] cookies = httpServletRequest.getCookies();
        User responseUser = securityService.getTokenByCookie(cookies);
        CreateReplyResponseDto createReplyRequestDto =  createReplyApplication.execute(responseUser, requestDto);
        return coreSuccessResponse(true, createReplyRequestDto, "대댓글 생성 성공", HttpStatus.CREATED.value());
    }

    @PutMapping()
    public UpdateReplyResponseDto updateReply(
            HttpServletRequest httpServletRequest,
            @RequestParam Long replyId,
            @RequestParam String content
    ) {
        Cookie[] cookies = httpServletRequest.getCookies();
        User responseUser = securityService.getTokenByCookie(cookies);
        updateReplyApplication.execute(responseUser, replyId, content);
        return null;
    }
}
