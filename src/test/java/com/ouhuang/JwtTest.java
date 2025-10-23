package com.ouhuang;

import com.ouhuang.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtTest {

    @Test
    public void test() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("user_id", 1);
        payload.put("mobile", "18230712114");
        String token = JwtUtil.generToken(payload);
        System.out.println(token);
        System.out.println(JwtUtil.parseToken(token));
    }
}
