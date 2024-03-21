package com.example.showmeyourability.service.teacherApplication;

import com.example.showmeyourability.comments.domain.QComments;
import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.shared.Exception.ErrorCode;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.teacher.application.FindTeacherApplication;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByIdResponseDto;
import com.example.showmeyourability.users.domain.GenderType;
import com.example.showmeyourability.users.domain.User;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FindTeacherApplicationTest {

    @Mock
    private JPAQueryFactory queryFactory;

    @Mock
    private JPAQuery<Teacher> jpaQuery;

    @InjectMocks
    private FindTeacherApplication findTeacherApplication;

    private User user;
    private Teacher teacher;

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

        teacher = Teacher.builder()
                .id(1L)
                .career("2")
                .skill("english")
                .user(user)
                .build();
    }
}