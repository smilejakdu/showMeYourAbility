package com.example.showmeyourability.teacher.infrastructure.repository;

import com.example.showmeyourability.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
