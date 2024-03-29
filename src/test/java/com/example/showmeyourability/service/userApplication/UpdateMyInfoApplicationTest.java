package com.example.showmeyourability.service.userApplication;

import com.example.showmeyourability.shared.Exception.ErrorCode;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.users.application.UpdateMyInfoApplication;
import com.example.showmeyourability.users.domain.GenderType;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto.UpdateUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto.UpdateUserResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateMyInfoApplicationTest {
    @Mock // 테스트 대상이 의존하는 객체
    private UserRepository userRepository;

    @InjectMocks // 테스트 대상
    private UpdateMyInfoApplication updateMyInfoApplication;

    private User user;
    private String email;
    private String password;

    @BeforeEach
    public void setUp() {
        email = "newUser@example.com";
        password = "password1234";

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // User 객체 생성
        user = User.builder()
                .id(1L)
                .email(email)
                .genderType(GenderType.MALE)
                .age(20)
                .img("img")
                .password(hashedPassword)
                .build();
    }

    @Test
    @DisplayName("내정보 수정 invalid 패스워드 테스트")
    public void testInvalidPasswordTest() {
        // given
        String invalidTestPassword = "123sdlac2asf";
        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder()
                .email(email)
                .age(1247)
                .password(invalidTestPassword) // invalid password
                .genderType(user.getGenderType())
                .img(user.getImg())
                .build();

        // when + then
        HttpExceptionCustom exception = assertThrows(HttpExceptionCustom.class, () -> {
            updateMyInfoApplication.execute(user, updateUserRequestDto);
        });
        System.out.println("exception = " + exception);

        Assertions.assertEquals(ErrorCode.INVALID_PARAMETER.getMessage(), exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    @DisplayName("내정보 수정 성공 테스트")
    public void testUpdateMyInfoSuccess() {
        // given
        String updatedEmail = "updatedUser@example.com"; // update email
        int updatedAge = 25; // update age
        GenderType updatedGender = GenderType.FEMALE;
        String updatedImg = "updatedImg.jpg"; // update img

        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder()
                .email(updatedEmail)
                .age(updatedAge)
                .genderType(updatedGender)
                .img(updatedImg)
                .password(user.getPassword())
                .build();

        User userForReturn = User.builder()
                .id(user.getId())
                .email(updatedEmail)
                .age(updatedAge)
                .img(updatedImg)
                .genderType(updatedGender)
                .build();

        // Mock userRepository behavior
        when(userRepository.save(any(User.class))).thenReturn(userForReturn);
        // when
        UpdateUserResponseDto result = updateMyInfoApplication.execute(user, updateUserRequestDto);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(user.getId(), result.getId());
        Assertions.assertEquals(updatedEmail, result.getEmail());
        Assertions.assertEquals(updatedAge, result.getAge());
        Assertions.assertEquals(updatedImg, result.getImg());
        Assertions.assertEquals(updatedGender, result.getGenderType());
    }
}
