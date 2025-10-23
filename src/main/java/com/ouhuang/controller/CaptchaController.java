package com.ouhuang.controller;

import com.ouhuang.entity.Captcha;
import com.ouhuang.service.CaptchaService;
import com.ouhuang.utils.HttpResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    /**
     * 获取图片验证码
     * @return HttpResp
     */
    @GetMapping("/image")
    public HttpResp imageCaptcha() {
        log.info("获取图片验证码");
        Captcha captcha = captchaService.getImageCaptcha();
        return HttpResp.success("获取成功", captcha);
    }

    /**
     * 用户获取验证码
     * @param payload
     * @return HttpResp
     */
    @PostMapping("/smsCode")
    public HttpResp smsCode(@RequestBody Map<String, String> payload) {
        log.info("获取短信验证码: {}", payload.get("mobile"));
        Object resp = captchaService.getSmsCode(payload);
        return resp != null ? HttpResp.success("获取验证码成功", resp) : HttpResp.fail("验证码错误");
    }
}
