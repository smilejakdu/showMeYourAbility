package com.example.showmeyourability.reply.infrastructure.repository;

import com.example.showmeyourability.reply.domain.Reply;
import com.example.showmeyourability.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
   Optional<Reply> findByIdAndUser(Long id, User user);
}