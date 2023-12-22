package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.domain.QComments;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByNameResponseDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import com.example.showmeyourability.users.domain.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTeacherByNameApplication {
    final private TeacherRepository teacherRepository;
    private final JPAQueryFactory queryFactory; // JPAQueryFactory 주입
    private final QTeacher qTeacher = QTeacher.teacher; // 클래스 수준의 QTeacher 인스턴스
    private final QUser qUser = QUser.user;
    private final QComments qComments = QComments.comments; // 클래스 수준의 QComments 인스턴스


    public FindTeacherByNameResponseDto findTeacherByName(
            String name
    ) {
        Teacher teacher = queryFactory
                .selectFrom(qTeacher)
                .join(qTeacher.user, qUser)
                .leftJoin(qTeacher.comments, qComments)
                .where(qUser.name.eq(name))
                .fetchOne();
        List<Comments> comments = queryFactory
                .selectFrom(qComments)
                .where(qComments.teacher.eq(teacher))
                .fetch();
        return new FindTeacherByNameResponseDto(
                teacher,
                comments
        );
    }
}
