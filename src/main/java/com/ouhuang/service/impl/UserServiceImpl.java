package com.ouhuang.service.impl;

import com.ouhuang.mapper.UserMapper;
import com.ouhuang.entity.User;
import com.ouhuang.service.UserService;
import com.ouhuang.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User select(Long userId) {
        return userMapper.select(userId);
    }

    @Override
    public void delete(Long userId) {
        userMapper.delete(userId);
    }

    @Override
    public void update(Long userId, User user) {
        user.setUser_id(userId);
        userMapper.update(user);
    }

    @Override
    public void update(String avatarUrl) {
        Map<String, Object> payload = ThreadLocalUtil.get();
        Long userId = ((Integer) payload.get("user_id")).longValue();
        User user = new User();
        user.setUser_id(userId);
        user.setAvatar(avatarUrl);
        userMapper.updateAvatar(user);
    }
}
