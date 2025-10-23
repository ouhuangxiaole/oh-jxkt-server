package com.ouhuang.controller;

import com.ouhuang.entity.LoginReq;
import com.ouhuang.entity.User;
import com.ouhuang.service.LoginService;
import com.ouhuang.utils.HttpResp;
import com.ouhuang.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户账密登陆
     * @param loginReq
     * @return HttpResp
     */
    @PostMapping("/password")
    public HttpResp loginPassword(@RequestBody LoginReq loginReq) {
        log.info("用户密码登陆: {}", loginReq.getMobile());
        LoginReq l = loginService.loginByPassword(loginReq);
        if (l == null)
            return HttpResp.fail("手机号或密码错误");
        Map<String, Object> payload = new HashMap<>();
        payload.put("user_id", l.getUser_id());
        payload.put("mobile", l.getMobile());
        String jwt = JwtUtil.generToken(payload);
        return HttpResp.success("登陆成功", jwt);
    }

    /**
     * 用户验证码登陆
     * @param loginReq
     * @return HttpResp
     */
    @PostMapping("/smsCode")
    public HttpResp loginSmsCode(@RequestBody LoginReq loginReq) {
        log.info("用户验证码登陆: {}", loginReq);
        LoginReq l = loginService.loginBySmsCode(loginReq);
        if (l == null)
            return HttpResp.fail("手机号或验证码错误");
        Map<String, Object> payload = new HashMap<>();
        payload.put("user_id", l.getUser_id());
        payload.put("mobile", l.getMobile());
        String jwt = JwtUtil.generToken(payload);
        return HttpResp.success("登陆成功", jwt);
    }

    /**
     * 用户注册接口
     * @param loginReq
     * @return HttpResp
     */
    @PostMapping("/register")
    public HttpResp register(@RequestBody LoginReq loginReq) {
        log.info("用户注册操作: {}", loginReq.getMobile());
        User user = loginService.register(loginReq);
        return user != null ? HttpResp.success("注册成功") : HttpResp.fail("注册失败, 手机号已注册");
    }
}
