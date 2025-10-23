package com.ouhuang.service;

import com.ouhuang.entity.LoginReq;
import com.ouhuang.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    LoginReq loginByPassword(LoginReq loginReq);

    LoginReq loginBySmsCode(LoginReq loginReq);

    User register(LoginReq loginReq);
}
