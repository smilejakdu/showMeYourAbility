package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.comments.domain.QComments;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByIdResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.TeacherDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTeacherApplication {
    private final JPAQueryFactory queryFactory; // JPAQueryFactory 주입
    private final QTeacher qTeacher = QTeacher.teacher; // 클래스 수준의 QTeacher 인스턴스
    private final QComments qComments = QComments.comments; // 클래스 수준의 QComments 인스턴스


    @Transactional
    public FindTeacherResponseDto findAllTeacher(int page, int size) {
        // 교사 정보와 평균 점수를 함께 조회
        List<TeacherDto> teacherDtos = queryFactory
                .select(Projections.constructor(TeacherDto.class,
                        qTeacher.id,
                        qTeacher.career,
                        qTeacher.user.email, // Email 필드 추가
                        qTeacher.skill,
                        qTeacher.user.id,
                        qComments.likes.avg().coalesce(0.0) // 평균 점수 계산 및 null 처리
                ))
                .from(qTeacher)
                .leftJoin(qTeacher.comments, qComments)
                .groupBy(qTeacher.id)
                .orderBy(qTeacher.user.email.asc())
                .offset((long) page * size)
                .limit(size)
                .fetch();

        // 총 페이지 수 계산
        long total = queryFactory
                .selectFrom(qTeacher)
                .fetchCount();

        int lastPage = (int) Math.ceil((double) total / size);

        return convertToFindTeacherResponseDto(lastPage, teacherDtos);
    }

    private FindTeacherResponseDto convertToFindTeacherResponseDto(
            int lastPage,
            List<TeacherDto> teachers
    ) {
        return FindTeacherResponseDto.builder()
                .lastPage(lastPage)
                .teachers(teachers)
                .build();
    }

    @Transactional
    public FindTeacherByIdResponseDto findOneTeacherById(
            Long teacherId
    ) {
        Teacher teacher = queryFactory
                .selectFrom(QTeacher.teacher)
                .where(QTeacher.teacher.id.eq(teacherId))
                .leftJoin(QTeacher.teacher.comments, QComments.comments)
                .fetchOne();
        System.out.println("teacher = " + teacher);

        if (teacher == null) {
            throw new HttpExceptionCustom(
                    false,
                    "해당하는 선생님 정보가 없습니다.",
                    HttpStatus.NOT_FOUND
            );
        }

        FindTeacherByIdResponseDto findTeacherByIdResponseDto = new FindTeacherByIdResponseDto();
        findTeacherByIdResponseDto.setTeacher(teacher);
        findTeacherByIdResponseDto.setCommentDtoList(teacher.getComments());
        return findTeacherByIdResponseDto;
    }
}
