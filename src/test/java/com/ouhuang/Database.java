package com.ouhuang;

import com.ouhuang.entity.Captcha;
import com.ouhuang.mapper.CaptchaMapper;
import com.ouhuang.mapper.UserMapper;
import com.ouhuang.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class Database {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CaptchaMapper captchaMapper;

    @Test
    public void insertUser() {
        for (int i = 0; i < 10; i++) {
            User user = User.getRandom();
            userMapper.insert(user);
        }
    }

    @Test
    public void insertCaptcha() {
        for (int i = 0; i < 10; i++) {
            String url = "http://loaclhost:3000/images/capter/code" + (i + 1) + ".png";
            // System.out.println(captcha);
            captchaMapper.update(i + 1, url);
        }
    }
}
