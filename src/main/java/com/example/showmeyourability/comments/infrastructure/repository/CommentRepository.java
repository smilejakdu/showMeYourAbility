package com.example.showmeyourability.comments.infrastructure.repository;

import com.example.showmeyourability.comments.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByTeacherId(Long teacherId);

    Optional<Comments> findByIdAndUserId(Long id, Long userId);
}
