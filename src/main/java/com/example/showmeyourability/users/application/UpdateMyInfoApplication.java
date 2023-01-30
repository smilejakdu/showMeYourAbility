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
            String email,
            UpdateUserRequestDto request
    ) {
        User user =  userRepository.findByEmail(email)
                .map(db-> {
                    db.setEmail(email);
                    return db;
                }).orElseThrow();

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new HttpException(
                    ErrorCode.INVALID_PARAMETER.getMessage(),
                    ErrorCode.INVALID_PARAMETER.getStatus()
            );
        }

        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        user.setGenderType(request.getGender());
        user.setPassword(request.getPassword());
        user.setImg(request.getImg());
        User savedUser = userRepository.save(user);

        UpdateUserResponseDto responseDto = new UpdateUserResponseDto();
        responseDto.setEmail(savedUser.getEmail());
        responseDto.setAge(savedUser.getAge());

        return responseDto;
    }
}
