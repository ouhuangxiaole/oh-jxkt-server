package com.ouhuang.service.impl;

import com.ouhuang.mapper.LoginMapper;
import com.ouhuang.mapper.UserMapper;
import com.ouhuang.entity.LoginReq;
import com.ouhuang.entity.User;
import com.ouhuang.service.LoginService;
import com.ouhuang.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User register(LoginReq loginReq) {
        // 手机号校验
        String mobile = loginReq.getMobile();
        // 如果用户已经存在 返回空
        if (loginMapper.exist(mobile) != null)
            return null;
        // 随机设置用户名 密码 用户注册
        User user= new User();
        user.setUsername(Generator.username(6));
        user.setPassword(Generator.password(6));
        user.setMobile(mobile);
        userMapper.insert(user);
        return userMapper.selectByMobile(mobile);
    }

    @Override
    public LoginReq loginByPassword(LoginReq loginReq) {
        return loginMapper.login(loginReq);
    }

    @Override
    public LoginReq loginBySmsCode(LoginReq loginReq) {
        String smsCode = loginReq.getSmsCode();
        // 校验验证码
        if (smsCode == null || !smsCode.equals("246810"))
            return null;
        // 校验手机号是否存在
        System.out.println(loginReq.getSmsCode());
        return loginMapper.select(loginReq);
    }
}
