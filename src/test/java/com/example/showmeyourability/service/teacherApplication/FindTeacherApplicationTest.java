package com.example.showmeyourability.service.teacherApplication;

import com.example.showmeyourability.comments.domain.QComments;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.teacher.application.FindTeacherApplication;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    @DisplayName("teacher find one 조회 실패")
    void doesNotExistFindOneTeacherByIdTestWhenTeacher() {
        Long teacherId = 133L;

        // queryFactory의 동작을 모의 처리합니다.
        // when
        when(queryFactory.selectFrom(QTeacher.teacher)).thenReturn(jpaQuery);
        when(jpaQuery.where(QTeacher.teacher.id.eq(teacherId))).thenReturn(jpaQuery);
        when(jpaQuery.leftJoin(QTeacher.teacher.comments, QComments.comments)).thenReturn(jpaQuery);
        when(jpaQuery.fetchOne()).thenReturn(null);

        assertThrows(HttpExceptionCustom.class, () -> {
            findTeacherApplication.findOneTeacherById(teacherId);
        }, "해당하는 선생님 정보가 없습니다.");
    }

    @Test
    @DisplayName("teacher find one 조회 성공")
    void successFindOneTeacherByIdTest() {
        Long teacherId = null;

        // queryFactory의 동작을 모의 처리합니다.
        // when
        when(queryFactory.selectFrom(QTeacher.teacher)).thenReturn(jpaQuery);
        when(jpaQuery.where(QTeacher.teacher.id.eq(teacherId))).thenReturn(jpaQuery);
        when(jpaQuery.leftJoin(QTeacher.teacher.comments, QComments.comments)).thenReturn(jpaQuery);
        when(jpaQuery.fetchOne()).thenReturn(null);

        assertThrows(HttpExceptionCustom.class, () -> {
            findTeacherApplication.findOneTeacherById(teacherId);
        }, "해당하는 선생님 정보가 없습니다.");
    }
}