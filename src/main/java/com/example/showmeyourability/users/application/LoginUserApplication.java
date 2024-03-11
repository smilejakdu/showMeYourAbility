package com.example.showmeyourability.users.application;

import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.shared.Service.securityService.SecurityService;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginUserApplication {


    private final UserRepository userRepository;
    private final SecurityService securityService;

    @Transactional
    public LoginUserResponseDto execute(
            LoginUserRequestDto request,
            HttpServletResponse response
    ) {
        // 각 메서드가 단일 책임을 갖도록 하여 유지보수를 용이하게 합니다.
        // 각각 메서드는 단일 책임원칙을 따르는것이 중요합니다.
        User user = validateUser(request);
        String token = securityService.createToken(user.getEmail());

        setCookie(response, token);
        return LoginUserResponseDto.builder()
                .email(user.getEmail())
                .token(token)
                .build();
    }

    private User validateUser(LoginUserRequestDto request) {
        return userRepository.findByEmail(request.getEmail())
                .map(dbUser -> {
                    if (!BCrypt.checkpw(request.getPassword(), dbUser.getPassword())) {
                        throw new HttpExceptionCustom(false, "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
                    }
                    return dbUser;
                })
                .orElseThrow(() -> new HttpExceptionCustom(false, "가입되어있지 않은 유저 입니다.", HttpStatus.BAD_REQUEST));
    }

    private void setCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("access-token", token);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}