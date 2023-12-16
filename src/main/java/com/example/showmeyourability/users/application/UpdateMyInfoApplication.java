package com.example.showmeyourability.users.application;

import com.example.showmeyourability.shared.Exception.ErrorCode;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto.UpdateUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto.UpdateUserResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMyInfoApplication {

    private final UserRepository userRepository;

    private void validatePassword(String requestPassword, String userPassword) {
        if (!BCrypt.checkpw(requestPassword, userPassword)) {
            throw new HttpExceptionCustom(
                    false,
                    "Invalid Password",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Transactional
    public UpdateUserResponseDto execute(
            User user,
            UpdateUserRequestDto request
    ) {
        validatePassword(request.getPassword(), user.getPassword());
        User updatedUser = User.builder()
                .email(request.getEmail())
                .age(request.getAge())
                .genderType(request.getGenderType())
                .password(request.getPassword())
                .img(request.getImg())
                .build();

        User savedUser = userRepository.save(updatedUser);

        return UpdateUserResponseDto.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .age(savedUser.getAge())
                .img(savedUser.getImg())
                .genderType(savedUser.getGenderType())
                .build();
    }
}
