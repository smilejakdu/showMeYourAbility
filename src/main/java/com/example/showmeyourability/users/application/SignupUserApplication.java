package com.example.showmeyourability.users.application;

import com.example.showmeyourability.shared.HttpException;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.CreateUserDto.CreateUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.CreateUserDto.CreateUserResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignupUserApplication {
    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponseDto signupUser(CreateUserRequestDto request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if(user.isPresent()) {
            throw new HttpException("이미 가입된 이메일 입니다.", HttpStatus.BAD_REQUEST);
        }

        User newUser = User.builder()
              .email(request.getEmail())
              .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
              .genderType(request.getGenderType())
              .age(request.getAge())
              .build();

        User saved = userRepository.save(newUser);

        CreateUserResponseDto responseDto = new CreateUserResponseDto();
        responseDto.setId(saved.getId());
        responseDto.setEmail(saved.getEmail());

        return responseDto;
    }
}
