package com.example.showmeyourability.users.application;

import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.CreateUserDto.CreateUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.CreateUserDto.CreateUserResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupUserApplication {
    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponseDto execute(CreateUserRequestDto request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new HttpExceptionCustom(
                            false,
                            "이미 가입된 이메일 입니다.",
                            HttpStatus.BAD_REQUEST);
                });

        User newUser = createUser(request);
        User savedUser = userRepository.save(newUser);
        return new CreateUserResponseDto(
                savedUser.getId(),
                savedUser.getEmail());
    }

    private User createUser(CreateUserRequestDto request) {
        return User.builder()
                .email(request.getEmail())
                .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
                .genderType(request.getGenderType())
                .age(request.getAge())
                .img(request.getImg())
                .build();
    }
}
