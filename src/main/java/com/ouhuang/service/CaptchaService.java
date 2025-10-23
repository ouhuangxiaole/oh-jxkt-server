package com.ouhuang.service;

import com.ouhuang.entity.Captcha;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CaptchaService {
    Captcha getImageCaptcha();

    Map getSmsCode(Map<String, String> payload);
}
