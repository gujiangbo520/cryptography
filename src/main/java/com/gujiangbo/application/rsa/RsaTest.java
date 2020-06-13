package com.gujiangbo.application.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;

/**
 * 高级加密方式:
 * 非对称加密RSA
 * 使用方式:
 * 私钥加密，公钥解密
 * 公钥加密，私钥解密
 *
 * @author gujiangbo
 */
public class RsaTest {

    public static void main(String[] args) {
        String message = "顾江波";
        String algorithm = "RSA";
        HashMap<EnumKey, Object> map = getkey(algorithm);
        /**
         * 保存公私钥
         */
        generateKeyToFile(algorithm, map, "a.pub", "a.pri");
        String encryptResult = encrypt(message, algorithm, map);
        System.out.println("加密后:" + encryptResult);

        String decryptResult = decrypt(encryptResult, algorithm, map);
        System.out.println("解密后:" + decryptResult);
    }

    /**
     * 生成密钥对并保存在本地文件中
     *
     * @param algorithm : 算法
     * @param map       : 公私钥
     * @param pubPath   : 公钥保存路径
     * @param priPath   : 私钥保存路径
     * @throws Exception
     */
    private static void generateKeyToFile(String algorithm, HashMap<EnumKey,
            Object> map, String pubPath, String priPath) {

        try {
            // 获取公钥
            PublicKey publicKey = (PublicKey) map.get(EnumKey.PUBLIC);
            // 获取私钥
            PrivateKey privateKey = (PrivateKey) map.get(EnumKey.PRIVATE);
            // 获取byte数组
            byte[] publicKeyEncoded = publicKey.getEncoded();
            byte[] privateKeyEncoded = privateKey.getEncoded();
            // 进行Base64编码
            String publicKeyString = Base64.encode(publicKeyEncoded);
            String privateKeyString = Base64.encode(privateKeyEncoded);
            // 保存文件
            FileUtils.writeStringToFile(new File(pubPath), publicKeyString, Charset.forName("UTF-8"));
            FileUtils.writeStringToFile(new File(priPath), privateKeyString, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 解密
     *
     * @param encryptMsg 密文
     * @param algorithm  加密算法
     * @param map        公私钥
     * @return 返回明文
     */
    public static String decrypt(String encryptMsg, String algorithm, HashMap<EnumKey, Object> map) {
        String decryptMsg = null;
        try {
            /**
             * 获取加密解密对象
             */
            Cipher cipher = Cipher.getInstance(algorithm);
            /**
             * 参数1: 解密模式
             * 参数2: 使用公钥解密
             */
            cipher.init(Cipher.DECRYPT_MODE, (PublicKey) map.get(EnumKey.PUBLIC));
            /**
             * 使用Base64解码
             */
            byte[] decode = Base64.decode(encryptMsg.getBytes());
            /**
             * 解码之后开始解密
             */
            byte[] decryptObj = cipher.doFinal(decode);
            decryptMsg = new String(decryptObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptMsg;
    }

    /**
     * 加密
     *
     * @param message   需要加密的文本
     * @param algorithm 加密算法
     * @param map       公私钥
     * @return
     */
    public static String encrypt(String message, String algorithm, HashMap<EnumKey, Object> map) {
        String encryptMsg = null;
        try {
            /**
             * 创建加密对象
             */
            Cipher cipher = Cipher.getInstance(algorithm);
            /**
             * 参数1：加密模式
             * 参数2：使用私钥加密
             */
            cipher.init(Cipher.ENCRYPT_MODE, (PrivateKey) map.get(EnumKey.PRIVATE));
            /**
             * 参数:传入原文数组进行加密处理
             */
            byte[] encryptObj = cipher.doFinal(message.getBytes());
            /**
             * 使用Base64进行编码
             */
            encryptMsg = Base64.encode(encryptObj);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return encryptMsg;
    }

    /**
     * 获取公钥/私钥
     *
     * @param algorithm 算法
     * @return 获取公钥私钥
     */
    public static HashMap<EnumKey, Object> getkey(String algorithm) {
        HashMap<EnumKey, Object> map = new HashMap<>();
        try {
            /**
             * 获取密钥对
             * keyPairGenerator 密钥对生成器
             */
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
            /**
             * 创建密钥对
             */
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            /**
             * 生成公钥
             */
            PublicKey publicKey = keyPair.getPublic();

            /**
             * 生成私钥
             */
            PrivateKey privateKey = keyPair.getPrivate();

            /**
             * 获取公钥数组
             */
            byte[] publicKeyEncoded = publicKey.getEncoded();
            /**
             * 获取私钥数组
             */
            byte[] privateKeyEncoded = privateKey.getEncoded();
            /**
             * 使用Base64 对公钥私钥进行编码
             */
            String publicKeyString = Base64.encode(publicKeyEncoded);
            String privateKeyString = Base64.encode(privateKeyEncoded);
            System.out.println(publicKeyString);
            System.out.println(privateKeyString);

            map.put(EnumKey.PRIVATE, privateKey);
            map.put(EnumKey.PUBLIC, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    enum EnumKey {
        PUBLIC, PRIVATE;
    }
}
