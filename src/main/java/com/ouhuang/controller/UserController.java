package com.ouhuang.controller;

import com.ouhuang.entity.User;
import com.ouhuang.service.UserService;
import com.ouhuang.utils.HttpResp;
import com.ouhuang.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户退出登陆
     * @param userId 用户id
     * @return
     */
    @PostMapping("logout/{id}")
    public HttpResp logout(@PathVariable("id") Long userId) {
        log.info("用户退出登陆: {}", userId);
        // 处理 Token
        return HttpResp.success("退出登陆成功");
    }

    /**
     * 查询用户详细信息
     * 在此已经做登陆校验和token校验
     * @return
     */
    @GetMapping
    public HttpResp select() {
        Long userId = ThreadLocalUtil.getUid();
        log.info("查询用户详细信息: {}", userId);
        User u = userService.select(userId);
        return HttpResp.success("获取用户信息成功", u.DTO());
    }

    /**
     * 更新用户信息
     * @param user 新的用户信息
     * @return
     */
    @PutMapping("/update")
    public HttpResp update(@RequestBody User user) {
        // 获取用户id
        Long userId = ThreadLocalUtil.getUid();
        if (user.getUser_id() != userId)
            return HttpResp.fail("用户ID错误");
        log.info("更新用户信息: {}", userId);
        userService.update(userId, user);
        return HttpResp.success("更新用户信息成功");
    }

    @RequestMapping("update/avatar")
    public HttpResp updateAvatar(@RequestBody String avatarUrl) {
        log.info("更新用户头像: {}", avatarUrl);
        userService.update(avatarUrl);
        return HttpResp.success("更新用户头像成功");
    }

    /**
     * 注销用户
     * @return
     */
    @DeleteMapping
    public HttpResp delete() {
        Map<String, Object> payload = ThreadLocalUtil.get();
        Long userId = ((Integer) payload.get("user_id")).longValue();
        log.info("用户注销: {}", userId);
        userService.delete(userId);
        return HttpResp.success("删除用户成功");
    }
}
