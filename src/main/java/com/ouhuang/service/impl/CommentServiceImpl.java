package com.ouhuang.service.impl;

import com.ouhuang.entity.Comment;
import com.ouhuang.mapper.CommentMapper;
import com.ouhuang.mapper.PostMapper;
import com.ouhuang.service.CommentService;
import com.ouhuang.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostMapper postMapper;

    @Override
    public Boolean sentComment(Long postId, String content) {
        Long userId = ThreadLocalUtil.getUid();
        if (postMapper.selectPost(postId) == null)
            return false;
        commentMapper.insertComment(userId, postId, content);
        return true;
    }

    @Override
    public Boolean likeComment(Long commentId) {
        Long userId = ThreadLocalUtil.getUid();
        if (commentMapper.selectComment(commentId) == null)
            return false;
        commentMapper.likeComment(userId, commentId);
        return true;
    }

    @Transactional
    @Override
    public Boolean deleteComment(Long commentId) {
        // Long userId = ThreadLocalUtil.getUserId();
        if (commentMapper.selectComment(commentId) == null)
            return false;
        // 删除评论以及相关点赞
        commentMapper.deleteComment(commentId);
        commentMapper.deleteLikes(commentId);
        return true;
    }

    @Transactional
    @Override
    public void deleteComments(Long postId) {
        // 删除帖子下所有评论和评论下的所有点赞信息
        List<Comment> comments = commentMapper.selectAllComments(postId);
        commentMapper.deleteComments(postId);
        for (Comment comment : comments)
            commentMapper.deleteLikes(comment.getComment_id());
    }
}
