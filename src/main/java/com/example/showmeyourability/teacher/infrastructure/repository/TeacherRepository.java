package com.example.showmeyourability.teacher.infrastructure.repository;

import com.example.showmeyourability.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t WHERE t.createdAt >= ?1 order by t.createdAt desc")
    List<Teacher> findByCreatedAtAfter(LocalDateTime dateTime);
}
