package com.gujiangbo.application.digest;

import java.security.MessageDigest;

/**
 * 数字摘要转换成16进制
 *
 * @author gujiangbo
 */
public class DigestTest02 {

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
        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            // 转成 16进制
            String s = Integer.toHexString(b & 0xff);
            //System.out.println(s);
            if (s.length() == 1) {
                // 如果生成的字符只有一个，前面补0
                s = "0" + s;
            }
            sb.append(s);
        }
        System.out.println(sb.toString());
    }
}
