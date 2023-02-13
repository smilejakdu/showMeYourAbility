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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS",
                    content = @Content(schema = @Schema(implementation = CreateUserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/signup")
    public CreateUserResponseDto signup(
            @RequestBody CreateUserRequestDto request
    ) {
        return signupUserApplication.signupUser(request);
    }

    @PostMapping("/login")
    public LoginUserResponseDto login(
            @RequestBody LoginUserRequestDto request
    ) {
        return loginUserApplication.execute(request);
    }

    @GetMapping()
    public FindUserByEmailResponseDto getMyInfoWithComment(
            @RequestHeader("access-token") String token
    ) {
//        filtering 라는것을 사용하면 된다 ??
        User responseUser = securityService.getSubject(token);
        return findUserByIdApplication.execute(responseUser);
    }

    @PutMapping()
    public UpdateUserResponseDto updateMyInfo(
            @RequestHeader("access-token") String token,
            @RequestBody UpdateUserRequestDto request
    ) {
        User responseUser = securityService.getSubject(token);
        return updateMyInfoApplication.updateMyInfo(responseUser, request);
    }
}
