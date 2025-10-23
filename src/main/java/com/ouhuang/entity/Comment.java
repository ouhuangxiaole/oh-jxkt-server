package com.ouhuang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long comment_id;
    private Long user_id;
    private Long post_id;
    private String content;
    private String avatar;
    private String username;
    private Integer likes_count;
    private Boolean isLike;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
}
