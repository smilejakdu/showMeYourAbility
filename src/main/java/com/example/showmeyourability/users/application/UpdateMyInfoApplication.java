package com.example.showmeyourability.users.application;

import com.example.showmeyourability.shared.Exception.ErrorCode;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto.UpdateUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto.UpdateUserResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMyInfoApplication {

    private final UserRepository userRepository;

    @Transactional
    public UpdateUserResponseDto execute(
            User user,
            UpdateUserRequestDto request
    ) {
        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new HttpExceptionCustom(
                    false,
                    ErrorCode.INVALID_PARAMETER.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
        User updatedUser = User.builder()
                .email(request.getEmail())
                .age(request.getAge())
                .genderType(request.getGenderType())
                .password(request.getPassword())
                .img(request.getImg())
                .build();

        User savedUser = userRepository.save(updatedUser);

        UpdateUserResponseDto responseDto = new UpdateUserResponseDto();
        responseDto.setEmail(savedUser.getEmail());
        responseDto.setAge(savedUser.getAge());

        return responseDto;
    }
}
