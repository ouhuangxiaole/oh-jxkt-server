package com.ouhuang.controller;

import com.ouhuang.entity.Comment;
import com.ouhuang.entity.Post;
import com.ouhuang.entity.PostDetail;
import com.ouhuang.service.CommentService;
import com.ouhuang.service.PostService;
import com.ouhuang.utils.HttpResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;
    /**
     * 查询帖子列表
     * @param page 页码
     * @param size 帖子数
     * @return HttpResp
     */
    @GetMapping
    public HttpResp selectPostList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        log.info("查询帖子: {}, {}", page, size);
        List<Post> data = postService.selectPostList(page, size);
        return HttpResp.success("获取成功", data);
    }

    /**
     * 查询帖子详情
     * @param postId 帖子id
     * @param page 评论页码
     * @param size 评论数
     * @return HttpResp
     */
    @GetMapping("/{id}")
    public HttpResp selectPostDetail(
            @PathVariable("id") Long postId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer size
    ) {
        log.info("查询帖子详情: {}", postId);
        PostDetail pd = postService.selectPost(postId, page, size);
        return pd != null ? HttpResp.success("获取帖子详情成功", pd) : HttpResp.fail("帖子不存在");
    }

    @PostMapping("/upload")
    public HttpResp uploadPost(@RequestBody PostDetail postDetail) {
        log.info("发布帖子: {}", postDetail);
        postService.uploadPost(postDetail);
        return HttpResp.success("发布成功");
    }

    /**
     * 删除帖子以及相关的评论和图片
     * @param postId
     * @return HttpResp
     */
    @DeleteMapping("/{id}")
    public HttpResp deletePost(@PathVariable("id") Long postId) {
        log.info("删除帖子: {}", postId);
        Boolean isDel = postService.deletePost(postId);
        return isDel ? HttpResp.success("删除成功") : HttpResp.fail("帖子不存在或已被删除");
    }

    /**
     * 点赞 / 取消点赞
     * @param postId 帖子id
     * @return
     */
    @PatchMapping("/like/{id}")
    public HttpResp likePost(@PathVariable("id") Long postId) {
        log.info("点赞操作, 帖子id: {}", postId);
        Boolean isLike = postService.likePost(postId);
        return isLike ? HttpResp.success("操作成功") : HttpResp.fail("帖子不存在或已被删除");
    }

    /**
     * 收藏 / 取消点赞
     * @param postId 帖子id
     * @return
     */
    @PatchMapping("/favo/{id}")
    public HttpResp favoPost(@PathVariable("id") Long postId) {
        log.info("收藏操作, 帖子id: {}", postId);
        Boolean isFavo = postService.favoPost(postId);
        return isFavo ? HttpResp.success("操作成功") : HttpResp.fail("帖子不存在或已被删除");
    }

    /**
     * 发送评论
     * @param postId
     * @return
     */
    @PutMapping("/comment/{id}")
    public HttpResp sentComment(
            @PathVariable("id") Long postId,
            @RequestBody Comment comment
    ) {
        log.info("发送评论, 帖子id: {}, 内容: {}", postId, comment.getContent());
        Boolean isIns = commentService.sentComment(postId, comment.getContent());
        return isIns ? HttpResp.success("发送成功") : HttpResp.fail("帖子不存在或已被删除");
    }

    /**
     * 评论点赞
     * @param commentId 评论id
     * @return
     */
    @PatchMapping("/comment/{id}")
    public HttpResp commentLikePost(@PathVariable("id") Long commentId) {
        log.info("给评论点赞, 评论id: {}", commentId);
        Boolean isUpd = commentService.likeComment(commentId);
        return isUpd ? HttpResp.success("操作成功") : HttpResp.fail("评论不存在或已被删除");
    }

    @DeleteMapping("/comment/{id}")
    public HttpResp deleteComment(@PathVariable("id") Long commentId) {
        log.info("删除评论, 评论id: {}", commentId);
        Boolean isDel = commentService.deleteComment(commentId);
        return isDel ? HttpResp.success("删除成功") : HttpResp.fail("评论不存在或已被删除");
    }
}
