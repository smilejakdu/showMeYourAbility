package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.domain.QComments;
import com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto.CommentDto;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByIdResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.TeacherDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTeacherApplication {
    private final JPAQueryFactory queryFactory; // JPAQueryFactory 주입
    private final TeacherRepository teacherRepository;

    @Transactional
    public FindTeacherResponseDto findAllTeacher(int page, int size) {
        QTeacher qTeacher = QTeacher.teacher; // Q 클래스 사용
        QComments qComments = QComments.comments; // Comments에 대한 Q 클래스

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
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new HttpExceptionCustom(
                        false,
                        "해당하는 선생님을 찾을 수 없습니다.",
                        HttpStatus.NOT_FOUND
                ));

        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comments comment : teacher.getComments()) {
            CommentDto commentDto = CommentDto.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .likes(comment.getLikes())
                    .userId(comment.getUser().getId())
                    .build();
            commentDtos.add(commentDto);
        }

        return FindTeacherByIdResponseDto.builder()
                .teacher(teacher)
                .commentDtoList(commentDtos)
                .build();
    }
}
