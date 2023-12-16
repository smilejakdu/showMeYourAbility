package com.example.showmeyourability.service.teacherApplication;

import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.teacher.application.CreateTeacherApplication;
import com.example.showmeyourability.teacher.application.FindTeacherApplication;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import com.example.showmeyourability.users.domain.GenderType;
import com.example.showmeyourability.users.domain.User;
import com.querydsl.jpa.impl.JPAQuery;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTeacherApplicationTest {
    @Mock
    private TeacherRepository teacherRepository;
    @InjectMocks
    private CreateTeacherApplication createTeacherApplication;

    private User user;
    @BeforeEach // 각 테스트 메소드 실행 전에 호출되는 메소드를 지정
    public void setup() {
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

//    @Test
//    @DisplayName("create teacher success")
//    void successCreateTeacherTest() {
//        Long teacherId = 133L;
//
//        Teacher newTeacherTest = Teacher.builder()
//                .id(1L)
//                .career("1")
//                .skill("math")
//                .user(user)
//                .build();
//
//        // when
//        // UserRepository의 모의 동작 설정
//        when(teacherRepository.save(newTeacherTest)).thenReturn(newTeacherTest);
//
//        // then
//        HttpExceptionCustom exception = Assertions.assertThrows(HttpExceptionCustom.class, () -> {
//            signUpUserApplication.execute(createUserRequestDto);
//        });
//
//        Assertions.assertEquals("이미 가입된 이메일 입니다.", exception.getMessage());
//        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
//    }
}
