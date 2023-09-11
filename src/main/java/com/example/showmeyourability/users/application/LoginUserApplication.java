package com.example.showmeyourability.users.application;

import com.example.showmeyourability.shared.Exception.HttpException;
import com.example.showmeyourability.shared.Service.SecurityService;
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
        try {
            User user = userRepository.findByEmail(request.getEmail())
                    .map(db-> {
                        if(!BCrypt.checkpw(request.getPassword(), db.getPassword())) {
                            throw new HttpException(
                                    false,
                                    "비밀번호가 일치하지 않습니다.",
                                    HttpStatus.BAD_REQUEST);
                        }
                        return db;
                    }).orElseThrow(() -> new HttpException(
                            false,
                            "가입되어있지 않은 유저 입니다.",
                            HttpStatus.BAD_REQUEST));

            String getToken = securityService.createToken(user.getEmail());

            LoginUserResponseDto responseDto = new LoginUserResponseDto();
            responseDto.setEmail(user.getEmail());
            responseDto.setToken(getToken);

            Cookie cookie = new Cookie("access-token", String.valueOf(getToken));
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            return LoginUserResponseDto.builder()
                    .email(user.getEmail())
                    .token(getToken)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("bad request");
        }
    }
}
