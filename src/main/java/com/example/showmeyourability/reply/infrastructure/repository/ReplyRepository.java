package com.example.showmeyourability.reply.infrastructure.repository;

import com.example.showmeyourability.reply.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
