package com.example.showmeyourability.users.application;

import com.example.showmeyourability.shared.Exception.ErrorCode;
import com.example.showmeyourability.shared.Exception.HttpException;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto.UpdateUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto.UpdateUserResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMyInfoApplication {

    private final UserRepository userRepository;

    @Transactional
    public UpdateUserResponseDto updateMyInfo(
            User user,
            UpdateUserRequestDto request
    ) {
        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new HttpException(
                    false,
                    ErrorCode.INVALID_PARAMETER.getMessage(),
                    ErrorCode.INVALID_PARAMETER.getStatus()
            );
        }
        User updatedUser = User.builder()
                .email(request.getEmail())
                .age(request.getAge())
                .genderType(request.getGender())
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
