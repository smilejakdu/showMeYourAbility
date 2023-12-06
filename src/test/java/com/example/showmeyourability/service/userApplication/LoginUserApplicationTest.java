package com.example.showmeyourability.service.userApplication;

import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.shared.Service.SecurityService;
import com.example.showmeyourability.users.application.LoginUserApplication;
import com.example.showmeyourability.users.domain.GenderType;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserRequestDto;
import com.example.showmeyourability.users.infrastructure.dto.LoginUserDto.LoginUserResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LoginUserApplicationTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private SecurityService securityService;
    @InjectMocks
    private LoginUserApplication loginUserApplication;

    private User user;

    @BeforeEach
    public void setUp() {
        String hashedPassword = BCrypt.hashpw("1234", BCrypt.gensalt());

        // User 객체 생성
        user = User.builder()
                .id(1L)
                .email("robertvsd1@gmail.com")
                .genderType(GenderType.MALE)
                .age(20)
                .img("img")
                .password(hashedPassword)
                .build();
    }

    @Test
    public void testExecuteUserNotFound() {
        // given
        String email = "nonexistent@example.com";
        String password = "password123";

        // HTTP 응답 모의 객체 생성
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // 로그인 요청 DTO 생성
        LoginUserRequestDto request = new LoginUserRequestDto();
        request.setEmail(email);
        request.setPassword(password);

        // when
        // UserRepository의 모의 동작 설정
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // then
        // HttpException이 예상대로 발생하는지 확인
        HttpExceptionCustom exception = Assertions.assertThrows(HttpExceptionCustom.class, () -> {
            loginUserApplication.execute(request, response);
        });

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        Assertions.assertEquals("가입되어있지 않은 유저 입니다.", exception.getMessage());
    }

    @Test
    public void testExecuteInvalidPassword() {
        // given
        String email = "test@example.com";
        String password = "password123";

        // 로그인 요청 DTO 생성
        LoginUserRequestDto request = new LoginUserRequestDto();
        request.setEmail(email);
        request.setPassword(password);

        // 올바른 이메일을 갖지만 패스워드 해시가 일치하지 않는 사용자 생성
        User user = User.builder()
                .email(email)
                .password(BCrypt.hashpw("incorrect_password", BCrypt.gensalt())) // 잘못된 패스워드 해시
                .build();

        // HTTP 응답 모의 객체 생성
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // when
        // UserRepository의 모의 동작 설정
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // then
        // HttpException이 예상대로 발생하는지 확인
        HttpExceptionCustom exception = Assertions.assertThrows(HttpExceptionCustom.class, () -> {
            loginUserApplication.execute(request, response);
        });

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        Assertions.assertEquals("비밀번호가 일치하지 않습니다.", exception.getMessage());
    }


    @Test
    public void testLoginSuccessUser() {
        // given
        String email = "test@example.com";
        String password = "password123";

        // 로그인 요청 DTO 생성
        LoginUserRequestDto request = new LoginUserRequestDto();
        request.setEmail(email);
        request.setPassword(password);

        // 올바른 이메일과 패스워드 해시를 갖는 사용자 생성
        User user = User.builder()
                .email(email)
                .password(BCrypt.hashpw(password, BCrypt.gensalt()))
                .build();

        String token = "test_token";

        // HTTP 응답 모의 객체 생성
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // when
        // UserRepository와 SecurityService의 모의 동작 설정
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(securityService.createToken(email)).thenReturn(token);

        // 메서드 실행
        LoginUserResponseDto responseDto = loginUserApplication.execute(request, response);

        // then
        // 응답이 null이 아니고 예상 값들을 확인
        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(email, responseDto.getEmail());
        Assertions.assertEquals(token, responseDto.getToken());

        // 쿠키가 설정되었는지 확인
        Mockito.verify(response).addCookie(Mockito.any(Cookie.class));
    }
}
