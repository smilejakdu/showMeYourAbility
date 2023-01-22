package com.example.showmeyourability.users;

import com.example.showmeyourability.shared.SecurityService;
import com.example.showmeyourability.users.application.FindUserByEmailApplication;
import com.example.showmeyourability.users.application.LoginUserApplication;
import com.example.showmeyourability.users.application.SignupUserApplication;
import com.example.showmeyourability.users.application.UpdateMyInfoApplication;
import com.example.showmeyourability.users.application.dto.FindUserByEmailDto.FindUserByEmailResponseDto;
import com.example.showmeyourability.users.application.dto.LoginUserDto.LoginUserRequestDto;
import com.example.showmeyourability.users.application.dto.LoginUserDto.LoginUserResponseDto;
import com.example.showmeyourability.users.application.dto.SignupUserDto.SignupUserRequestDto;
import com.example.showmeyourability.users.application.dto.SignupUserDto.SignupUserResponseDto;
import com.example.showmeyourability.users.application.dto.UpdateUserDto.UpdateUserRequestDto;
import com.example.showmeyourability.users.application.dto.UpdateUserDto.UpdateUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final LoginUserApplication loginUserApplication;

    private final FindUserByEmailApplication findUserByIdApplication;

    private final UpdateMyInfoApplication updateMyInfoApplication;

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

    @PutMapping()
    public UpdateUserResponseDto updateMyInfo(
            @RequestHeader("access-token") String token,
            @RequestBody UpdateUserRequestDto request
    ) {
        String responseEmail = securityService.getSubject(token);
        return updateMyInfoApplication.updateMyInfo(responseEmail, request);
    }
}
