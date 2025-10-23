package com.ouhuang.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {

    private Long user_id;
    private String mobile;
    private String password;
    private String smsCode;
}
