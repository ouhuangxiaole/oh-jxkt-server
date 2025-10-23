package com.ouhuang.service;

import com.ouhuang.entity.Post;
import com.ouhuang.entity.PostDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<Post> selectPostList(Integer page, Integer size);

    PostDetail selectPost(Long postId, Integer page, Integer size);

    Boolean deletePost(Long postId);

    Boolean likePost(Long postId);

    Boolean favoPost(Long postId);

    void uploadPost(PostDetail postDetail);
}
