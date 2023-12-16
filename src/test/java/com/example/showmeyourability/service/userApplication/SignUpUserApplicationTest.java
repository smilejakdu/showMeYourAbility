package com.example.showmeyourability.service.userApplication;

import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.users.application.SignupUserApplication;
import com.example.showmeyourability.users.domain.GenderType;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.CreateUserDto.CreateUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.CreateUserDto.CreateUserResponseDto;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SignUpUserApplicationTest {
    @Mock // 테스트 대상이 의존하는 객체
    private UserRepository userRepository;
    @InjectMocks // 테스트 대상
    private SignupUserApplication signUpUserApplication;
    // 테스트 대상인 빈에는 @InjectMocks를 사용하고
    // 가짜로 대체할 의존성에는 @Mock을 사용한다.

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
    @DisplayName("회원가입 존재하는 이메일 입니다.")
    public void existUserSignUpTest() {
        // given
        String existEmail = "newUser@example.com";
        String hashedPassword = BCrypt.hashpw("swepowfwer124908", BCrypt.gensalt());

        // HTTP 응답 모의 객체 생성
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
        createUserRequestDto.setEmail(existEmail);
        createUserRequestDto.setPassword(hashedPassword);
        createUserRequestDto.setGenderType(GenderType.MALE);
        createUserRequestDto.setImg("img");
        createUserRequestDto.setPhone("010-1234-1234");
        createUserRequestDto.setAge(20);

        // when
        // UserRepository의 모의 동작 설정
        when(userRepository.findByEmail(existEmail)).thenReturn(Optional.ofNullable(user));

        // then
        HttpExceptionCustom exception = Assertions.assertThrows(HttpExceptionCustom.class, () -> {
            signUpUserApplication.execute(createUserRequestDto);
        });

        Assertions.assertEquals("이미 가입된 이메일 입니다.", exception.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    @DisplayName("회원가입 성공")
    public void successUserSignUpTest() {
        // given
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
        createUserRequestDto.setEmail("newuser@example.com");
        createUserRequestDto.setPassword("swepowfwer124908");
        createUserRequestDto.setGenderType(GenderType.FEMALE);
        createUserRequestDto.setAge(20);
        createUserRequestDto.setImg("img_path");
        String hashedPassword = BCrypt.hashpw(createUserRequestDto.getPassword(), BCrypt.gensalt());

        User newUser = User.builder()
                .email(createUserRequestDto.getEmail())
                .password(hashedPassword)
                .genderType(createUserRequestDto.getGenderType())
                .age(createUserRequestDto.getAge())
                .img(createUserRequestDto.getImg())
                .build();

        when(userRepository.findByEmail(createUserRequestDto.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        // when
        CreateUserResponseDto result = signUpUserApplication.execute(createUserRequestDto);
        System.out.println("result = " + result);
        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(createUserRequestDto.getEmail(), result.getEmail());
    }
}
