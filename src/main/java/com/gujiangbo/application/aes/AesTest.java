package com.gujiangbo.application.aes;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesTest {
    public static void main(String[] args) throws Exception {
        String key = "123456781234567812345678";
        String transformation = "AES";
        String algorithm = "AES";
        String message = "顾江波";
        String encryptMsg = encrypt(key, algorithm, transformation, message);
        System.out.println("密文:" + encryptMsg);
        String decryptMsg = decrypt(key, algorithm, transformation, encryptMsg);
        System.out.println("解密:" + decryptMsg);

    }

    /**
     * 解密
     *
     * @param key            密钥
     * @param algorithm      算法
     * @param transformation 类型
     * @param encryptMsg     密文
     * @return
     * @throws Exception
     */
    public static String decrypt(String key, String algorithm, String transformation, String encryptMsg) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return new String(cipher.doFinal(Base64.decode(encryptMsg.getBytes())));
    }

    /**
     * 加密
     *
     * @param key            密钥
     * @param algorithm      加密算法
     * @param transformation 加密类型
     * @param message        明文
     * @return
     */
    public static String encrypt(String key, String algorithm, String transformation, String message) throws Exception {

        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return Base64.encode(cipher.doFinal(message.getBytes()));
    }
}
