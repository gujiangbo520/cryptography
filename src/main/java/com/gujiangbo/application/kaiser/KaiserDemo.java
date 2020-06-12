package com.gujiangbo.application.kaiser;

import org.apache.commons.lang3.StringUtils;

/**
 * 凯撒位移加密
 * @author gujiangbo
 */
public class KaiserDemo {

    public static void main(String[] args) {
        /**
         * 原始报文
         */
        String msg = "gujiangbo";
        /**
         * 位移量
         */
        int key = 3;
        String encrypt = encrypt(msg, key);
        System.out.println("密文:" + encrypt);
        String decrypt = decrypt(encrypt, key);
        System.out.println("明文:" + decrypt);


    }

    /**
     * 位移加密
     *
     * @param msg 明文
     * @param key 位移量
     * @return 密文
     */
    public static String encrypt(String msg, int key) {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isBlank(msg)) {
            return null;
        }
        for (char c : msg.toCharArray()) {
            int asciiCode = c;
            asciiCode = asciiCode + key;
            sb.append((char) asciiCode);
        }
        return sb.toString();
    }

    /**
     * 解密
     *
     * @param decryptMsg 密文
     * @param key        位移量
     * @return 明文
     */
    public static String decrypt(String decryptMsg, int key) {
        if (StringUtils.isBlank(decryptMsg)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (char c : decryptMsg.toCharArray()) {
            int ascillCode = c;
            ascillCode -= key;
            char result = (char) ascillCode;
            sb.append(result);
        }
        return sb.toString();
    }
}
