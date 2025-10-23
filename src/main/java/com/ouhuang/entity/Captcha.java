package com.ouhuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Captcha {
    private String key;
    private String url;
    private String md5;
}
