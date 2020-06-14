package com.gujiangbo.application.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;

/**
 * 使用公钥加密，私钥解密
 *
 * @author gujiangbo
 */
public class RsaTest03 {

    public static void main(String[] args) throws Exception {
        /**
         * 明文
         */
        String msg = "锄禾日当午，汗滴禾下土；谁知盘中餐，粒粒皆辛苦。";
        /**
         * 加密算法
         */
        String algorithm = "RSA";
        /**
         * 获取公私钥
         */
        HashMap<Key, Object> map = getKey(algorithm);
        String encryMsg = encryptRSA(msg, algorithm, (PublicKey) map.get(Key.PUBLIC));
        System.out.println("密文:" + encryMsg);
        String result = decryptRSA(encryMsg, algorithm, (PrivateKey) map.get(Key.PRIVATE));
        System.out.println("明文:" + result);

    }

    /**
     * 解密
     *
     * @param encryptMsg 密文
     * @param algorithm  算法
     * @param privateKey 私钥
     * @return 返回明文
     * @throws Exception
     */
    public static String decryptRSA(String encryptMsg, String algorithm, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] msg = cipher.doFinal(Base64.decode(encryptMsg.getBytes()));
        return new String(msg);
    }

    /**
     * 加密
     *
     * @param msg       原文
     * @param algorithm 算法
     * @param publicKey 公钥
     * @return 加密对象
     */
    public static String encryptRSA(String msg, String algorithm, PublicKey publicKey) throws Exception {

        /**
         * 生成加密器
         */
        Cipher cipher = Cipher.getInstance(algorithm);
        /**
         * 初始化加密器
         */
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptMsg = cipher.doFinal(msg.getBytes());
        return Base64.encode(encryptMsg);
    }

    /**
     * 获取公私钥
     *
     * @param algorithm 加密算法
     * @return 公私钥Hash
     */
    public static HashMap<Key, Object> getKey(String algorithm) throws Exception {
        HashMap<Key, Object> map = new HashMap<>();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        map.put(Key.PRIVATE, privateKey);
        map.put(Key.PUBLIC, publicKey);
        return map;
    }

    /**
     * 公私钥枚举值
     */
    enum Key {
        PUBLIC, PRIVATE
    }

}
