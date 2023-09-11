package com.example.showmeyourability.reply;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.reply.application.CreateReplyApplication;
import com.example.showmeyourability.reply.infrastructure.dto.CreateReplyDto.CreateReplyRequestDto;
import com.example.showmeyourability.shared.Service.SecurityService;
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
    public void createReply(
            @CookieValue("access-token") String accessToken,
            @RequestBody CreateReplyRequestDto request
    ) {
        User responseUser = securityService.getSubject(accessToken);
        Comments comments = request.getComments();
        String content = request.getContent();
        createReplyApplication.execute(content, responseUser, comments);
    }
}
