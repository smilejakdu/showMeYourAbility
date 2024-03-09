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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.showmeyourability.shared.CoreSuccessResponse.coreSuccessResponse;

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
    @Operation(
            summary = "회원가입",
            description = "회원가입"
    )
    public CoreSuccessResponse signup(
            @RequestBody CreateUserRequestDto request
    ) {
        CreateUserResponseDto createUserResponseDto = signupUserApplication.execute(request);
        return coreSuccessResponse(true, createUserResponseDto, "회원가입 성공", HttpStatus.CREATED.value());
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "로그인",
            description = "로그인"
    )
    public CoreSuccessResponse login(
            @RequestBody LoginUserRequestDto request,
            HttpServletResponse response
    ) {
        LoginUserResponseDto loginUserResponseDto = loginUserApplication.execute(request, response);
        return coreSuccessResponse(true,loginUserResponseDto, "로그인 성공", HttpStatus.OK.value());
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "내정보 불러오기",
            description = "내정보 불러오기"
    )
    public CoreSuccessResponse getMyInfoWithComment(
            HttpServletRequest request
    ) {
        Cookie[] cookies = request.getCookies();
        String tokenString = securityService.getTokenByCookie(cookies);
        User responseUser = securityService.getSubject(tokenString);
        FindUserByEmailResponseDto findUserByEmailResponseDto = findUserByIdApplication.execute(responseUser.getEmail());
        return coreSuccessResponse(true, findUserByEmailResponseDto, "로그인 성공", HttpStatus.OK.value());
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "내정보 수정",
            description = "내정보 수정"
    )
    public CoreSuccessResponse updateMyInfo(
            HttpServletRequest request,
            @RequestBody UpdateUserRequestDto updateUserRequestDto
    ) {
        Cookie[] cookies = request.getCookies();
        String tokenString = securityService.getTokenByCookie(cookies);
        User responseUser = securityService.getSubject(tokenString);
        UpdateUserResponseDto updateUserResponseDto = updateMyInfoApplication.execute(
                responseUser,
                updateUserRequestDto
        );
        return coreSuccessResponse(true, updateUserResponseDto, "내정보 수정 성공", HttpStatus.OK.value());
    }
}
