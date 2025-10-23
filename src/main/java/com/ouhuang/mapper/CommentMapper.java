package com.ouhuang.mapper;

import com.ouhuang.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment where comment_id = #{commentId}")
    Comment selectComment(Long commentId);

    @Select("select * from comment where post_id = #{id} limit #{start}, #{size}")
    List<Comment> selectComments(Long id, Integer start, Integer size);

    @Select("select * from comment where post_id = #{id}")
    List<Comment> selectAllComments(Long postId);

    @Delete("delete from comment where post_id = #{postId}")
    void deleteComments(Long postId);

    @Select("select (exists(select * from comment_like where user_id = #{userId} and comment_id = #{commentId}))")
    Boolean isLikeComment(Long userId, Long commentId);

    @Insert("insert into comment_like(user_id, comment_id, create_time) values(#{userId}, #{commentId}, now())")
    void likeComment(Long userId, Long commentId);

    @Insert("insert into comment(user_id, post_id, content, create_time) value (#{userId}, #{postId}, #{content}, now())")
    void insertComment(Long userId, Long postId, String content);

    @Delete("delete from comment where comment_id = #{commentId}")
    void deleteComment(Long commentId);

    @Delete("delete from comment_like where comment_id = #{commentId}")
    void deleteLikes(Long commentId);

}
