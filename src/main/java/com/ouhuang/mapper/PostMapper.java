package com.ouhuang.mapper;

import com.ouhuang.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {

    @Select("select * from post limit #{start}, #{size}")
    List<Post> selectPostList(Integer start, Integer size);

    @Select("select * from post where post_id = #{id}")
    PostDetail selectPost(Long id);

    @Delete("delete from post where post_id = #{postId}")
    void deletePost(Long postId);

    @Select("select (exists(select * from post_like where user_id = #{userId} and post_id = #{postId}))")
    Boolean isLikePost(Long userId, Long postId);

    @Select("select (exists(select * from post_favo where user_id = #{userId} and post_id = #{postId}))")
    Boolean isFavoPost(Long userId, Long postId);

    @Delete("delete from post_like where user_id = #{userId} and post_id = #{postId}")
    void cancelLike(Long userId, Long postId);

    @Insert("insert into post_like(user_id, post_id) values (#{userId}, #{postId})")
    void insertPostLike(Long userId, Long postId);

    @Delete("delete from post_favo where user_id = #{userId} and post_id = #{postId}")
    void cancelFavo(Long userId, Long postId);

    @Insert("insert into post_favo(user_id, post_id) values (#{userId}, #{postId})")
    void insertPostFavo(Long userId, Long postId);

    @Update("update post set likes_count = likes_count + #{count} where post_id = #{postId}")
    void modifyPostLikeCount(Long postId, Integer count);

    @Update("update post set favos_count = favos_count + #{count} where post_id = #{postId}")
    void modifyPostFavoCount(Long postId, Integer count);

    @Select("select * from post where user_id = #{userId} and post_id = #{postId}")
    PostDetail userHavePost(Long userId, Long postId);

    @Delete("delete from post_like where post_id = #{postId}")
    void deleteLikes(Long postId);

    @Delete("delete from post_favo where post_id = #{postId}")
    void deleteFavos(Long postId);

    @Insert("insert into post(user_id, content) values(#{user_id}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "post_id")
    void insertPost(PostDetail postDetail);
}
