package com.example.showmeyourability.users;

import com.example.showmeyourability.shared.SecurityService;
import com.example.showmeyourability.users.application.FindUserByEmailApplication;
import com.example.showmeyourability.users.application.LoginUserApplication;
import com.example.showmeyourability.users.application.SignupUserApplication;
import com.example.showmeyourability.users.infrastructure.dto.FindUserByEmailDto.FindUserByEmailResponseDto;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserResponseDto;
import com.example.showmeyourability.users.infrastructure.dto.SignupUserDto.SignupUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.SignupUserDto.SignupUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final LoginUserApplication loginUserApplication;

    private final FindUserByEmailApplication findUserByIdApplication;

    private final SignupUserApplication signupUserApplication;
    private final SecurityService securityService;

    @PostMapping("/signup")
    public SignupUserResponseDto signup(
            @RequestBody SignupUserRequestDto request
    ) {
        return signupUserApplication.signupUser(request);
    }

    @PostMapping("/login")
    public LoginUserResponseDto login(@RequestBody LoginUserRequestDto request) {
        return loginUserApplication.LoginUser(request);
    }

    @GetMapping()
    public FindUserByEmailResponseDto getMyInfoWithComment(
            @RequestHeader("access-token") String token
    ) {
        String responseEmail = securityService.getSubject(token);
        return findUserByIdApplication.findUserByEmail(responseEmail);
    }
}
