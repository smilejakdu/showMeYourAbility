package com.example.showmeyourability.reply;

import com.example.showmeyourability.reply.application.CreateReplyApplication;
import com.example.showmeyourability.reply.application.UpdateReplyApplication;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyRequestDto;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyResponseDto;
import com.example.showmeyourability.reply.infrastructure.dto.UpdateReplyResponseDto;
import com.example.showmeyourability.users.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.showmeyourability.shared.Service.SecurityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "reply", description = "대댓글 API")
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final CreateReplyApplication createReplyApplication;

    private final UpdateReplyApplication updateReplyApplication;

    private final SecurityService securityService;

    @PostMapping()
    public CreateReplyResponseDto createReply(
            @RequestHeader("access-token") String token,
            @RequestBody CreateReplyRequestDto requestDto
    ) {
        User responseUser = securityService.getSubject(token);
        return createReplyApplication.execute(responseUser, requestDto);
    }

    @PutMapping()
    public UpdateReplyResponseDto updateReply(
            @RequestHeader("access-token") String token,
            @RequestParam Long replyId,
            @RequestParam String content
    ) {
        User responseUser = securityService.getSubject(token);
        updateReplyApplication.execute(responseUser, replyId, content);
        return null;
    }
}
