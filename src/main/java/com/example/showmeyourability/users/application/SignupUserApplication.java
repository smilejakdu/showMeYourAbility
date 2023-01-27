package com.example.showmeyourability.users.application;

import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.CreateUserDto.CreateUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.CreateUserDto.CreateUserResponseDto;
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
    public CreateUserResponseDto signupUser(CreateUserRequestDto request) {
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

        CreateUserResponseDto responseDto = new CreateUserResponseDto();
        responseDto.setId(saved.getId());
        responseDto.setEmail(saved.getEmail());

        return responseDto;
    }
}
