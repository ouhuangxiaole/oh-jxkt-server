package com.ouhuang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private Long user_id;
    private Long post_id;
    private String content;
    private Boolean isLike;
    private Boolean isFavo;
    private Integer likes_count;
    private Integer favos_count;
    private Integer comms_count;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;

    private UserDTO userInfo;
    private List<PostImage> images;

}
