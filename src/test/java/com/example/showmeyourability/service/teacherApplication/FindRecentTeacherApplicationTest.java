package com.example.showmeyourability.service.teacherApplication;

import com.example.showmeyourability.teacher.application.FindRecentTeacherApplication;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindRecentTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.TeacherDto;
import com.example.showmeyourability.users.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindRecentTeacherApplicationTest {

    @Mock
    private JPAQueryFactory queryFactory;

    @InjectMocks
    private FindRecentTeacherApplication findRecentTeacherApplication;

    private List<TeacherDto> teacherDtoList;
    private User user;

    LocalDateTime fourDaysAgo = LocalDateTime.now().minusDays(4);
    LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);

    @BeforeEach
    public void setup() {
        String password = "password1234";
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user = User.builder()
                .id(1L)
                .name("robert")
                .email("robert@gmail.com")
                .password(hashPassword)
                .build();


        teacherDtoList = Arrays.asList(
                TeacherDto.builder()
                        .id(1L)
                        .career("career")
                        .skill("skill")
                        .createdAt(fourDaysAgo)
                        .userId(user.getId())
                        .email(user.getEmail())
                        .build(),
                TeacherDto.builder()
                        .id(2L)
                        .career("career2")
                        .skill("skill2")
                        .userId(user.getId())
                        .createdAt(threeDaysAgo)
                        .email(user.getEmail())
                        .build()
        );
    }

    @Test
    @DisplayName("최근 4일간 가입한 선생님들 조회")
    public void testExecute() {
        // given
        QTeacher qTeacher = QTeacher.teacher;

        JPAQuery<Teacher> mockQuery = mock(JPAQuery.class);
        when(queryFactory.selectFrom(qTeacher)).thenReturn(mockQuery);
        when(mockQuery.where(any(BooleanExpression.class))).thenReturn(mockQuery);
        when(mockQuery.innerJoin(qTeacher.user)).thenReturn(mockQuery);
        when(mockQuery.fetch()).thenReturn(
                Arrays.asList(
                        Teacher.builder()
                                .id(1L)
                                .career("career")
                                .skill("skill")
                                .createdAt(fourDaysAgo) // createdAt 값을 수동으로 설정
                                .user(user)
                                .build(),
                        Teacher.builder()
                                .id(2L)
                                .career("career2")
                                .skill("skill2")
                                .createdAt(threeDaysAgo) // createdAt 값을 수동으로 설정
                                .user(user)
                                .build()
                )
        );

        // when
        FindRecentTeacherResponseDto findRecentTeacherResponseDto = findRecentTeacherApplication.execute();
        System.out.println("findRecentTeacherResponseDto = " + findRecentTeacherResponseDto);
        // then
        assertEquals(teacherDtoList, findRecentTeacherResponseDto.getTeacherDtoList());
    }
}
