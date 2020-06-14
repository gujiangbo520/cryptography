package com.gujiangbo.application.digest;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.MessageDigest;

/**
 * 数字摘要
 *
 * @author gujiangbo
 */
public class DigestTest {

    public static void main(String[] args) throws Exception {
        /**
         * 原文
         */
        String msg = "顾江波";
        /**
         * 摘要算法
         */
        String algorithm = "MD5";
        /**
         * 获取数字摘要对象
         */
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        /**
         * 获取数字摘要结果字符数组
         */
        byte[] digest = messageDigest.digest();
        /**
         * 使用Base64对数组编码
         */
        String encode = Base64.encode(digest);
        System.out.println(encode);
    }
}
