package com.ouhuang.service.impl;

import com.ouhuang.controller.LoginController;
import com.ouhuang.entity.Captcha;
import com.ouhuang.mapper.CaptchaMapper;
import com.ouhuang.service.CaptchaService;
import com.ouhuang.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private CaptchaMapper captchaMapper;

    @Override
    public Captcha getImageCaptcha() {
        Captcha captcha = captchaMapper.select();
        captcha.setMd5(MD5Util.generateMD5(captcha.getMd5()));
        return captcha;
    }

    @Override
    public Map getSmsCode(Map<String, String> payload) {
        String mobile = payload.get("mobile");
        String code = payload.get("captchaCode");
        String key = payload.get("captchaKey");
        Captcha captcha = captchaMapper.verify(code, key);
        if (captcha == null)
            return null;
        // 模拟调用api 给用户发短信
        System.out.println(mobile);
        Map<String, String> resp = new HashMap<>();
        resp.put("smsCode", "246810");
        resp.put("message", "开发环境, 验证码为246810");
        return resp;
    }
}
