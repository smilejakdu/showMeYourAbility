package com.example.showmeyourability.users.application;

import com.example.showmeyourability.shared.SecurityService;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
            Optional<User> user = userRepository.findByEmail(request.getEmail())
                    .map(db-> {
                        if(!BCrypt.checkpw(request.getPassword(), db.getPassword())) {
                            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
                        }
                        return db;
                    });
//           map 만 돌리면 무시가 된다. orElse

            if (user.isEmpty()) {
                throw new RuntimeException("가입되어있지 않은 유저 입니다.");
            }

//          TODO : .......?
//          orElseThrow 를 이용해서 한줄로 사용할 수 있다.
            if (!BCrypt.checkpw(request.getPassword(), user.map(User::getPassword).orElseThrow())) {
                throw new RuntimeException("비밀번호가 일치하지 않습니다.");
            }

            String email = user.map(User::getEmail)
                    .orElseThrow(() -> new RuntimeException("가입되어있지 않은 유저 입니다."));
            String getToken = securityService.createToken(email);

            LoginUserResponseDto responseDto = new LoginUserResponseDto();
            responseDto.setEmail(user.map(User::getEmail).orElseThrow());
            responseDto.setToken(getToken);

            Cookie cookie = new Cookie("access-token", String.valueOf(getToken));
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException("bad request");
        }
    }
}
