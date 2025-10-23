package com.ouhuang.service;

import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Boolean sentComment(Long userId, String content);

    Boolean likeComment(Long commentId);

    Boolean deleteComment(Long commentId);

    void deleteComments(Long postId);
}
