package com.ouhuang.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Generator {
    private static final String DIGIT = "1234567890";
    private static final String LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SPECIAL = "@#$%&";

    private static String generate(String chars, Integer length) {
        if (length <= 0)
            throw new IllegalArgumentException("长度必须大于0");

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    public static String mobile() {
        return "1" + generate("3456789", 1) + generate(DIGIT, 9);
    }

    public static String username(int length) {
        String chars = DIGIT + LETTER;
        return "用户" + generate(chars, length);
    }

    public static String password(int length) {
        String chars = DIGIT + LETTER + SPECIAL;
        return generate(chars, length);
    }

    public static String email(int length) {
        List<String> postfix = Arrays.asList("qq", "163", "sina", "sohu", "aliyun");
        int index = new Random().nextInt(postfix.size());
        return generate(DIGIT, length) + "@" + postfix.get(index) + ".com";
    }
}
