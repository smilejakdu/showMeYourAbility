package com.example.showmeyourability.users.application;

import com.example.showmeyourability.shared.SecurityService;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginUserApplication {
    private final UserRepository userRepository;

    private final SecurityService securityService;

    @Transactional
    public LoginUserResponseDto LoginUser(LoginUserRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .map(db->{
                    if(!BCrypt.checkpw(request.getPassword(), db.getPassword())) {
                        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
                    }
                    return db;
                }).orElseThrow();
//      존재하지 않는 user 입니다로 변경해야함
        String email = user.getEmail();
        String getToken = securityService.createToken(email);

        LoginUserResponseDto responseDto = new LoginUserResponseDto();
        responseDto.setEmail(email);
        responseDto.setToken(getToken);
        return responseDto;
    }
}
