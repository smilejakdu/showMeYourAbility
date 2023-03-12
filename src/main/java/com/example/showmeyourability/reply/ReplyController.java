package com.example.showmeyourability.reply;


import com.example.showmeyourability.reply.application.CreateReplyApplication;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyRequestDto;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyResponseDto;
import com.example.showmeyourability.shared.SecurityService;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final CreateReplyApplication createReplyApplication;

    private final SecurityService securityService;

    @PostMapping()
    public CreateReplyResponseDto createReply(
            @RequestHeader("access-token") String token,
            @RequestBody CreateReplyRequestDto requestDto
    ) {
        User responseUser = securityService.getSubject(token);
        return createReplyApplication.execute(responseUser, requestDto);
    }
}