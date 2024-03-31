package com.example.showmeyourability.comments.application;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.infrastructure.dto.FindCommentDto.CommentDto;
import com.example.showmeyourability.comments.infrastructure.repository.CommentRepository;
import com.example.showmeyourability.shared.CoreSuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindCommentByTeacherIdApplication {
    private final CommentRepository commentRepository;
    private final RedisTemplate<String, Object> redisTemplate;


    @Transactional(readOnly = true)
    public CoreSuccessResponse execute(Long teacherId) {
        String cacheKey = String.format("comment:%s", teacherId);
        List<CommentDto> commentDtoList = fetchCommentsFromCache(cacheKey);

        if (commentDtoList == null) {
            List<Comments> comments = commentRepository.findByTeacherId(teacherId);
            commentDtoList = getCommentDtoList(comments);
            cacheComments(cacheKey, commentDtoList);
        }

        return buildResponse(commentDtoList);
    }

    private List<CommentDto> fetchCommentsFromCache(String cacheKey) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return (List<CommentDto>) valueOperations.get(cacheKey);
    }

    private void cacheComments(String cacheKey, List<CommentDto> commentDtoList) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(cacheKey, commentDtoList);
    }

    private CoreSuccessResponse buildResponse(List<CommentDto> commentDtoList) {
        return CoreSuccessResponse
                .builder()
                .ok(true)
                .message("댓글 조회 성공")
                .data(commentDtoList)
                .build();
    }

    private List<CommentDto> getCommentDtoList(List<Comments> comments) {
        return comments.stream().map(this::getCommentDto).collect(Collectors.toList());
    }

    private CommentDto getCommentDto(Comments comment) {
        return CommentDto.builder()
                    .userId(comment.getUser().getId())
                    .teacherId(comment.getTeacher().getId())
                    .likes(comment.getLikes())
                    .content(comment.getContent())
                    .build();
    }
}