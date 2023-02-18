package com.example.showmeyourability.users;

import com.example.showmeyourability.shared.SecurityService;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
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

    @ApiOperation(value = "test", notes = "테스트입니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "page not found!")
    })
    @PostMapping("/signup")
    public CreateUserResponseDto signup(
            @RequestBody CreateUserRequestDto request
    ) {
        return signupUserApplication.signupUser(request);
    }

    @PostMapping("/login")
    public LoginUserResponseDto login(
            @RequestBody LoginUserRequestDto request,
            HttpServletResponse response
    ) {
        return loginUserApplication.execute(request, response);
    }

    @GetMapping()
    public FindUserByEmailResponseDto getMyInfoWithComment(
            @CookieValue("access-token") String token
    ) {
        User responseUser = securityService.getSubject(token);
        return findUserByIdApplication.execute(responseUser);
    }

    @PutMapping()
    public UpdateUserResponseDto updateMyInfo(
            @CookieValue("access-token") String token,
            @RequestBody UpdateUserRequestDto request
    ) {
        User responseUser = securityService.getSubject(token);
        return updateMyInfoApplication.updateMyInfo(responseUser, request);
    }
}
