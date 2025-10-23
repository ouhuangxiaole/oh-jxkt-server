package com.ouhuang.interceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouhuang.utils.HttpResp;
import com.ouhuang.utils.JwtUtil;
import com.ouhuang.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        String token = req.getHeader("Authorization");
        try {
            Map<String, Object> payload = JwtUtil.parseToken(token);
            ThreadLocalUtil.set(payload);
            return true;
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setContentType("application/json;charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();
            HttpResp httpResp = new HttpResp(401, "用户未登陆或用户登陆已经过期", null);
            resp.getWriter().write(mapper.writeValueAsString(httpResp));
            return false;
        }
    }
}
