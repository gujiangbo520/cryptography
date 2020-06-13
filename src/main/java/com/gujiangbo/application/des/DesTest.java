package com.gujiangbo.application.des;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES 加密
 */
public class DesTest {

    public static void main(String[] args) throws Exception {
        String message = "顾江波";
        String algorithm = "DES";
        String transformation = "DES";
        String key = "12345678";
        String encryptMsg = encrypt(key, algorithm, transformation, message);
        System.out.println("加密:" + encryptMsg);
        String decryptMsg = decrypt(key, algorithm, transformation, encryptMsg);
        System.out.println("解密:" + decryptMsg);
    }

    /**
     * 解密
     *
     * @param key            密钥
     * @param algorithm      解密算法
     * @param transformation 解密类型
     * @param encryptMsg     密文
     * @return
     */
    public static String decrypt(String key, String algorithm, String transformation, String encryptMsg) throws Exception {
        if (StringUtils.isBlank(encryptMsg)) {
            return null;
        }
        /**
         * 创建加密解密器
         * 参数：解密类型
         */
        Cipher cipher = Cipher.getInstance(transformation);
        /**
         * 密钥规则
         *参数1：密钥字符数组
         *参数2：DES算法
         */
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        /**
         * 解密初始化
         *参数1：表示模式，有加密模式，解密模式
         *参数2：密钥规则
         */
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        /**
         * 开始解密，如果密文使用Base64编码，需要先解码
         */
        byte[] result = cipher.doFinal(Base64.decode(encryptMsg.getBytes()));
        return new String(result);
    }

    /**
     * @param key            密钥
     * @param algorithm      加密算法
     * @param transformation 加密类型
     * @param message        明文
     * @return
     */
    public static String encrypt(String key, String algorithm, String transformation, String message) throws Exception {
        String encryptMsg = null;
        if (StringUtils.isBlank(message)) {
            return encryptMsg;
        }
        Cipher cipher = Cipher.getInstance(transformation);
        /**
         * 密钥规则
         * 参数1: 密钥，字节数组
         * 参数2： 算法，使用DES
         */
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        /**
         * 对加密进行初始化
         * 参数1：表示模式，有加密模式，解密模式
         * 参数2：密钥规则
         */
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        /**
         * 对明文进行加密，
         * 参数：明文字节数组
         */
        byte[] bytes = cipher.doFinal(message.getBytes());
        /**
         * 解决加密后乱码问题，使用Base64进行编码
         */
        encryptMsg = Base64.encode(bytes);
        return encryptMsg;
    }
}
