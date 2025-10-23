package com.ouhuang.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResp {
    private Integer status;
    private String message;
    private Object data;

    public static HttpResp success(String message) {
        return new HttpResp(200, message, null);
    }

    public static HttpResp success(String message, Object data) {
        return new HttpResp(200, message, data);
    }

    public static HttpResp fail(String message) {
        return new HttpResp(400, message, null);
    }
}
