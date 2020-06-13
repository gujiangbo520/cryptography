package com.gujiangbo.application.base64;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Base64Test {
    public static void main(String[] args) {
        // 1：MQ== 表示一个字节，不够三个字节，所以需要后面通过 == 号补齐
        System.out.println(Base64.encode("1".getBytes()));
        System.out.println(Base64.encode("12".getBytes()));
        System.out.println(Base64.encode("123".getBytes()));
        // 江苏:中文占6个字节，6 * 8 = 48 ，刚刚好被整除，所以没有等号
        System.out.println(Base64.encode("江苏".getBytes()));
    }
}
