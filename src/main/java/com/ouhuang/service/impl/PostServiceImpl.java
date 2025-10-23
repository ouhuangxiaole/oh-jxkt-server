package com.ouhuang.service.impl;

import com.ouhuang.mapper.CommentMapper;
import com.ouhuang.mapper.ImageMapper;
import com.ouhuang.mapper.PostMapper;
import com.ouhuang.mapper.UserMapper;
import com.ouhuang.entity.*;
import com.ouhuang.service.CommentService;
import com.ouhuang.service.PostService;
import com.ouhuang.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public PostDetail selectPost(Long postId, Integer page, Integer size) {
        // 查询帖子基本信息
        PostDetail post = postMapper.selectPost(postId);
        if (post == null)
            return null;
        // 检验该次请求是否在有效登陆状态下
        Map<String, Object> payload = ThreadLocalUtil.get();
        Boolean isLogin = payload != null;
        Long queryUserId = isLogin ? ThreadLocalUtil.getUid() : -1;
        // 查询发帖人的信息和以及帖子的图片信息
        post.setImages(imageMapper.selectImages(post.getPost_id()));
        post.setUserInfo(userMapper.select(post.getUser_id()).DTO());
        // 发起请求的用户是否点赞该条帖子
        post.setIsLike(isLogin ? postMapper.isLikePost(queryUserId, postId) : false);
        post.setIsFavo(isLogin ? postMapper.isFavoPost(queryUserId, postId) : false);
        // 查询评论下的所有用户信息
        Integer start = (page - 1) * size;
        List<Comment> comments = commentMapper.selectComments(postId, start, size);
        for (Comment comment : comments) {
            // 查询评论发送者的信息
            User u = userMapper.select(comment.getUser_id());
            comment.setAvatar(u.getAvatar());
            comment.setUsername(u.getUsername());
            // 查询请求用户是否点赞该条评论
            Boolean isLike = isLogin ? commentMapper.isLikeComment(queryUserId, comment.getComment_id()) : false;
            comment.setIsLike(isLike);
        }
        post.setComments(comments);
        return post;
    }

    @Override
    public List<Post> selectPostList(Integer page, Integer size) {
        Integer start = (page - 1) * size;
        List<Post> posts = postMapper.selectPostList(start, size);
        // 检验该次请求是否在有效登陆状态下
        Long queryUserId = ThreadLocalUtil.getUid();
        Boolean isLogin = queryUserId != -1;
        // 查询每条帖子的发帖人信息和图片
        for (Post post : posts) {
            Long postId = post.getPost_id();
            post.setImages(imageMapper.selectImages(postId));
            post.setUserInfo(userMapper.select(post.getUser_id()).DTO());
            post.setIsLike(isLogin ? postMapper.isLikePost(queryUserId, postId) : false);
            post.setIsFavo(isLogin ? postMapper.isFavoPost(queryUserId, postId) : false);
        }
        return posts;
    }

    @Transactional
    @Override
    public Boolean deletePost(Long postId) {
        Long userId = ThreadLocalUtil.getUid();
        // 查询该用户是否有该帖子
        PostDetail p = postMapper.userHavePost(userId, postId);
        if (p == null)
            return false;
        // 删除帖子 删除帖子下的图片 删除帖子下的评论
        postMapper.deletePost(postId);
        postMapper.deleteLikes(postId);
        postMapper.deleteFavos(postId);
        imageMapper.deleteImages(postId);
        commentService.deleteComments(postId);
        return true;
    }

    @Transactional
    @Override
    public Boolean likePost(Long postId) {
        Long userId = ThreadLocalUtil.getUid();
        // 查询帖子是否存在
        if (postMapper.selectPost(postId) == null)
            return false;
        Boolean isLike = postMapper.isLikePost(userId, postId);
        if (isLike)
            postMapper.cancelLike(userId, postId);
        else
            postMapper.insertPostLike(userId, postId);
        postMapper.modifyPostLikeCount(postId, isLike ? -1 : 1);
        return true;
    }

    @Transactional
    @Override
    public Boolean favoPost(Long postId) {
        Long userId = ThreadLocalUtil.getUid();
        // 查询帖子是否存在
        if (postMapper.selectPost(postId) == null)
            return false;
        Boolean isFavo = postMapper.isFavoPost(userId, postId);
        if (isFavo)
            postMapper.cancelFavo(userId, postId);
        else
            postMapper.insertPostFavo(userId, postId);
        postMapper.modifyPostFavoCount(postId, isFavo ? 1 : -1);
        return true;
    }

    @Transactional
    @Override
    public void uploadPost(PostDetail postDetail) {
        System.out.println(postDetail);
        Long uid = ThreadLocalUtil.getUid();
        log.info("上传帖子的用户: {}", uid);
        postDetail.setUser_id(uid);
        postMapper.insertPost(postDetail);
        Long postId = postDetail.getPost_id();
        log.info("上传帖子生成id: {}", postId);
        for (PostImage postImage : postDetail.getImages()) {
            imageMapper.insertImage(postId, postImage.getUrl());
        }
    }
}
