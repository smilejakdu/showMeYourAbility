package com.example.showmeyourability.service.teacherApplication;

import com.example.showmeyourability.teacher.application.FindTeacherApplication;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByIdResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class FindTeacherApplicationTest {

    @Mock
    private JPAQueryFactory queryFactory;

    @InjectMocks
    private FindTeacherApplication findTeacherApplication;

    @Test
    @DisplayName("teacher find one 조회 실패")
    void findOneTeacherByIdTestWhenTeacherDoesNotExist() {
        Long teacherId = 133L;

        FindTeacherByIdResponseDto findTeacherByIdResponseDto = new FindTeacherByIdResponseDto();
        findTeacherByIdResponseDto.setTeacher(null);
        findTeacherByIdResponseDto.setCommentDtoList(null);

        assertThrows(NotFoundException.class, () -> {
            findTeacherApplication.findOneTeacherById(teacherId);
        });
    }
}