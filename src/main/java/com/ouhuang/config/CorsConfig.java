package com.ouhuang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 1. 允许任何域名使用
        config.addAllowedOriginPattern("*");

        // 2. 允许任何请求头
        config.addAllowedHeader("*");

        // 3. 允许任何方法（POST、GET等）
        config.addAllowedMethod("*");

        // 4. 允许携带凭证（如cookie）
        config.setAllowCredentials(true);

        // 5. 配置生效路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // 6. 返回新的CorsFilter
        return new CorsFilter(source);
    }
}