package com.ouhuang.mapper;

import com.ouhuang.entity.PostImage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {

    @Select("select * from post_image where post_id = #{postId}")
    List<PostImage> selectImages(Long postId);

    @Delete("delete from post_image where post_id = #{postId}")
    void deleteImages(Long postId);

    @Select("select image_id from post_image where url = #{url}")
    Long selectImageId(String url);

    @Insert("insert into post_image(post_id, url) values(#{postId}, #{url})")
    void insertImage(Long postId, String url);
}
