package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.comments.domain.QComments;
import com.example.showmeyourability.shared.Service.redisService.RedisService;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.TeacherDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindTeacherApplication {
    private final static String CACHE_KEY = "findAllTeacher";
    private final JPAQueryFactory queryFactory; // JPAQueryFactory 주입
    private final QTeacher qTeacher = QTeacher.teacher;
    private final QComments qComments = QComments.comments;
    private final RedisService redisService;
    private final ObjectMapper objectMapper;


    private FindTeacherResponseDto findTeacherFromDatabase(int page, int size) {
       // 데이터베이스에서 데이터를 조회 합니다.
        List<TeacherDto> teacherDtoList = getTeacherDtos(page, size, qTeacher);
        long totalCount = queryFactory
                .selectFrom(QTeacher.teacher)
                .fetchCount();

        int lastPage = (int) Math.ceil((double) totalCount / size);

        FindTeacherResponseDto returnValue = new FindTeacherResponseDto(lastPage, teacherDtoList);
        saveToCache(returnValue);

        return FindTeacherResponseDto.builder()
                .lastPage(lastPage)
                .teachers(teacherDtoList)
                .build();
    }

    private Optional<FindTeacherResponseDto> findTeacherFromCache(int page, int size) {
        String cacheKey = CACHE_KEY + page + size;
        String cacheValue = redisService.getValue(cacheKey);
        if (cacheValue != null && !cacheValue.isEmpty()) {
            try {
                System.out.println("cacheValue: " + cacheValue);
                return Optional.of(objectMapper.readValue(cacheValue, FindTeacherResponseDto.class));
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse JSON from cache", e);
            }
        }
        return Optional.empty();
    }


    @Transactional(readOnly = true) // 읽기 전용 트랜잭션
    public FindTeacherResponseDto execute(int page, int size) {
        return findTeacherFromCache(page, size).orElseGet(() -> findTeacherFromDatabase(page, size));
    }


    private List<TeacherDto> getTeacherDtos(
            int page,
            int size,
            QTeacher qTeacher
    ) {
        List<TeacherDto> rawTeacherDtos = queryFactory
                .select(Projections.constructor(TeacherDto.class,
                        qTeacher.id,
                        qTeacher.career,
                        qTeacher.user.email,
                        qTeacher.user.id,
                        qTeacher.skill,
                        qComments.likes.avg().coalesce(0.0),
                        qTeacher.createdAt
                ))
                .from(QTeacher.teacher)
                .leftJoin(QTeacher.teacher.comments, QComments.comments)
                .groupBy(QTeacher.teacher.id)
                .orderBy(QTeacher.teacher.user.email.asc())
                .offset((long) page * size)
                .limit(size)
                .fetch();

        // 후처리: 평균 좋아요 수를 소수점 둘째자리까지 반올림
        return rawTeacherDtos.stream()
                .map(dto -> {
                    BigDecimal roundedAvgLikes = BigDecimal.valueOf(dto.getAvgScore())
                            .setScale(2, RoundingMode.HALF_UP); // 소수점 둘째자리 반올림
                    return new TeacherDto(
                            dto.getId(),
                            dto.getCareer(),
                            dto.getEmail(),
                            dto.getUserId(),
                            dto.getSkill(),
                            roundedAvgLikes.doubleValue(), // 반올림된 값 적용
                            dto.getCreatedAt());
                })
                .collect(Collectors.toList());
    }

    private void saveToCache(FindTeacherResponseDto returnValue) {
        // 캐시에 데이터를 저장
        try {
            String cacheValue = objectMapper.writeValueAsString(returnValue);
            redisService.setValue(CACHE_KEY, cacheValue);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save to cache", e);
        }
    }
}
