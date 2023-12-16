package com.example.showmeyourability.users;

import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.shared.Service.SecurityService;
import com.example.showmeyourability.users.application.FindUserByEmailApplication;
import com.example.showmeyourability.users.application.LoginUserApplication;
import com.example.showmeyourability.users.application.SignupUserApplication;
import com.example.showmeyourability.users.application.UpdateMyInfoApplication;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.CreateUserDto.CreateUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.CreateUserDto.CreateUserResponseDto;
import com.example.showmeyourability.users.infrastructure.dto.FindUserDto.FindUserByEmailResponseDto;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserResponseDto;
import com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto.UpdateUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto.UpdateUserResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "user", description = "유저 API")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final LoginUserApplication loginUserApplication;

    private final FindUserByEmailApplication findUserByIdApplication;

    private final UpdateMyInfoApplication updateMyInfoApplication;

    private final SignupUserApplication signupUserApplication;

    private final SecurityService securityService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public CoreSuccessResponse signup(
            @RequestBody CreateUserRequestDto request
    ) {
        CreateUserResponseDto createUserResponseDto =  signupUserApplication.execute(request);
        return CoreSuccessResponse.builder()
                .ok(true)
                .message("회원가입 성공")
                .data(createUserResponseDto)
                .build();
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public CoreSuccessResponse login(
            @RequestBody LoginUserRequestDto request,
            HttpServletResponse response
    ) {
        LoginUserResponseDto loginUserResponseDto = loginUserApplication.execute(request, response);
        return CoreSuccessResponse.builder()
                .ok(true)
                .message("로그인 성공")
                .data(loginUserResponseDto)
                .build();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public FindUserByEmailResponseDto getMyInfoWithComment(
            @CookieValue("access-token") String token
    ) {
        User responseUser = securityService.getSubject(token);
        String email = responseUser.getEmail();
        return findUserByIdApplication.execute(email);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public UpdateUserResponseDto updateMyInfo(
            @CookieValue("access-token") String token,
            @RequestBody UpdateUserRequestDto request
    ) {
        User responseUser = securityService.getSubject(token);
        return updateMyInfoApplication.updateMyInfo(responseUser, request);
    }
}
