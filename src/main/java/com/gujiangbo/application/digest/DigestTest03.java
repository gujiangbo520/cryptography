package com.gujiangbo.application.digest;

import java.security.MessageDigest;

public class DigestTest03 {
    public static void main(String[] args) throws Exception {
        // 原文
        String input = "aa";
        // 算法
        String algorithm = "MD5";
        // 获取数字摘要对象
        String md5 = getDigest(input, "MD5");
        System.out.println("MD5:" + md5);

        String sha1 = getDigest(input, "SHA-1");
        System.out.println("SHA-1:" + sha1);

        String sha256 = getDigest(input, "SHA-256");
        System.out.println("SHA-256:" + sha256);

        String sha512 = getDigest(input, "SHA-512");
        System.out.println("SHA-512:" + sha512);
    }

    private static String toHex(byte[] digest) throws Exception {

//        System.out.println(new String(digest));
        // base64编码
//        System.out.println(Base64.encode(digest));
        // 创建对象用来拼接
        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            // 转成 16进制
            String s = Integer.toHexString(b & 0xff);
            if (s.length() == 1) {
                // 如果生成的字符只有一个，前面补0
                s = "0" + s;
            }
            sb.append(s);
        }
        System.out.println("16进制数据的长度：" + sb.toString().getBytes().length);
        return sb.toString();
    }

    private static String getDigest(String input, String algorithm) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 消息数字摘要
        byte[] digest = messageDigest.digest(input.getBytes());
        System.out.println("密文的字节长度:" + digest.length);

        return toHex(digest);
    }
}
