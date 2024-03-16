package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.shared.Service.redisService.RedisService;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindRecentTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.TeacherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindRecentTeacherApplication {
    private final static String CACHE_KEY = "recentTeacher";
    private final JPAQueryFactory queryFactory;
    private final RedisService redisService;
    private final ObjectMapper objectMapper;

    @Transactional
    public FindRecentTeacherResponseDto execute() {
        return getRecentTeacherFromCache().orElseGet(this::getRecentTeacherFromDatabase);
    }

    private Optional<FindRecentTeacherResponseDto> getRecentTeacherFromCache() {
        String cacheValue = redisService.getValue(CACHE_KEY);
        if (cacheValue != null && !cacheValue.isEmpty()) {
            // 캐시에 저장된 값이 있으면 반환
            try {
                return Optional.of(objectMapper.readValue(cacheValue, FindRecentTeacherResponseDto.class));
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse JSON from cache", e);
            }
        }

        return Optional.empty();
    }

    private FindRecentTeacherResponseDto getRecentTeacherFromDatabase() {
        // 데이터베이스에서 데이터를 조회
        LocalDateTime fourDaysAgo = LocalDateTime.now().minusDays(4);
        QTeacher qTeacher = QTeacher.teacher;
        List<TeacherDto> teacherDtoList = getTeacherDtos(fourDaysAgo, qTeacher);

        FindRecentTeacherResponseDto returnValue = new FindRecentTeacherResponseDto(teacherDtoList);

        // 캐시에 데이터를 저장
        saveToCache(returnValue);

        return returnValue;
    }

    private List<TeacherDto> getTeacherDtos(LocalDateTime fourDaysAgo, QTeacher qTeacher) {
        return queryFactory.selectFrom(qTeacher)
                .where(qTeacher.createdAt.after(fourDaysAgo)) // 4일 전부터의 선생님 정보를 가져옵니다.
                .innerJoin(qTeacher.user)
                .orderBy(qTeacher.createdAt.desc())
                .limit(4)
                .fetch()
                .stream()
                .map(teacher -> TeacherDto.builder()
                        .id(teacher.getId())
                        .career(teacher.getCareer())
                        .email(teacher.getUser().getEmail())
                        .skill(teacher.getSkill())
                        .createdAt(teacher.getCreatedAt())
                        .userId(teacher.getUser().getId())
                        .build())
                .collect(Collectors.toList());
    }

    private void saveToCache(FindRecentTeacherResponseDto returnValue) {
        try {
            String jsonValue = objectMapper.writeValueAsString(returnValue);
            redisService.setValue(CACHE_KEY, jsonValue);
            System.out.println("Saved to cache: " + jsonValue);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }
}