package com.example.showmeyourability.service.userApplication;

import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.shared.Service.securityService.SecurityService;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LoginUserApplicationTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private SecurityService securityService;
    @InjectMocks
    private LoginUserApplication loginUserApplication;

    private User user;
    private String email;
    private String password;

    @BeforeEach
    public void setUp() {
        email = "robertvsd1@gmail.com";
        password = "password1234";

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

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
    @DisplayName("로그인 유저 없음 테스트")
    public void testExecuteUserNotFound() {
        // given
        String doesNotFoundEmail = "nonexistent@example.com";

        // HTTP 응답 모의 객체 생성
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // 로그인 요청 DTO 생성
        LoginUserRequestDto request = new LoginUserRequestDto();
        request.setEmail(doesNotFoundEmail);
        request.setPassword(password);

        // when
        // UserRepository의 모의 동작 설정
        when(userRepository.findByEmail(doesNotFoundEmail)).thenReturn(Optional.empty());

        // then
        // HttpException이 예상대로 발생하는지 확인
        HttpExceptionCustom exception = Assertions.assertThrows(HttpExceptionCustom.class, () -> loginUserApplication.execute(request, response));

        System.out.println("exception = " + exception);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        Assertions.assertEquals("가입되어있지 않은 유저 입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("로그인 패스워드 실패 테스트")
    public void testExecuteInvalidPassword() {
        // given
        String password = "passwod5632";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());


        // 로그인 요청 DTO 생성
        LoginUserRequestDto request = new LoginUserRequestDto();
        request.setEmail(email);
        request.setPassword(hashedPassword);

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
    @DisplayName("로그인 성공 테스트")
    public void testLoginSuccessUser() {
        // given
        LoginUserRequestDto request = new LoginUserRequestDto();
        request.setEmail(email);
        request.setPassword(password);

        // HTTP 응답 모의 객체 생성
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // when
        // UserRepository와 SecurityService의 모의 동작 설정
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // SecurityService 모의 객체 설정
        when(securityService.createToken(anyString())).thenReturn("mocked_token");

        // LoginUserApplication 수동 생성
        loginUserApplication = new LoginUserApplication(userRepository, securityService);


        // 메서드 실행
        LoginUserResponseDto responseDto = loginUserApplication.execute(request, response);

        // then
        // 응답이 null이 아니고 예상 값들을 확인
        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(email, responseDto.getEmail());
        Assertions.assertEquals("mocked_token", responseDto.getToken());

        // 쿠키가 설정되었는지 확인
        Mockito.verify(response).addCookie(Mockito.any(Cookie.class));
    }
}
