package com.example.showmeyourability.users.application;

import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.application.dto.SignupUserDto.SignupUserRequestDto;
import com.example.showmeyourability.users.application.dto.SignupUserDto.SignupUserResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupUserApplication {
    private final UserRepository userRepository;

    @Transactional
    public SignupUserResponseDto signupUser(SignupUserRequestDto request) {
        userRepository.findByEmail(request.getEmail())
                .map(db->{
                    throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
                });

        User newUser = new User();
        String password = request.getPassword();
        String bcryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        newUser.setEmail(request.getEmail());
        newUser.setPassword(bcryptPassword);
        User saved = userRepository.save(newUser);

        SignupUserResponseDto responseDto = new SignupUserResponseDto();
        responseDto.setUserId(saved.getId());
        responseDto.setEmail(saved.getEmail());

        return responseDto;
    }
}
