package com.ouhuang.service;

import com.ouhuang.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User select(Long userId);

    void update(Long userId, User user);

    void update(String avatarUrl);

    void delete(Long userId);
}
