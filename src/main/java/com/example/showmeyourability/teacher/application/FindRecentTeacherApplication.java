package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindRecentTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.TeacherDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindRecentTeacherApplication {
    private final TeacherRepository teacherRepository;

    @Transactional
    public FindRecentTeacherResponseDto execute() {
        LocalDateTime fourDaysAgo = LocalDateTime.now().minusDays(4);
//      index 가 걸려있는 칼럼은 sortiong 은 괜춘
//      soring 은 싱글 쓰레드로 동작하기 때문에 디비가 성능이 좋아도 싱글 스레드 -> 부하 심하다.
//      index 를 걸어주자.
//      distinct -> 디비에서 쿼리를 한다.
//      group by 자체가 부하가 심하다. -> 권장하지 않고 -> 자주 사용한다면 따로 빼는것이 좋다.
        List<TeacherDto> teacherDtoList = teacherRepository.findByCreatedAtAfter(fourDaysAgo)
                .stream()
                .map(teacher -> TeacherDto.builder()
                        .id(teacher.getId())
                        .career(teacher.getCareer())
                        .email(teacher.getUser().getEmail())
                        .skill(teacher.getSkill())
                        .userId(teacher.getUser().getId())
                        .build())
                .toList();

        return FindRecentTeacherResponseDto.builder()
                .teacherDtoList(teacherDtoList)
                .build();
    }
}
