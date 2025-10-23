package com.ouhuang.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String generateMD5(String content) {
        try {
            // 获取 MD5 摘要实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算 MD5 哈希值
            byte[] hashBytes = md.digest(content.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5算法不存在", e);
        }
    }
}