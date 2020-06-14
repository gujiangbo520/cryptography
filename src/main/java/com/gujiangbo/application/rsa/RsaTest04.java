package com.gujiangbo.application.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 保存公私钥
 *
 * @author gujiangbo
 */
public class RsaTest04 {

    public static void main(String[] args) throws Exception {
        /**
         * 明文
         */
        String msg = "顾江波";
        /**
         * 加密算法
         */
        String algorithm = "RSA";

        /**
         *  生成密钥对并保存在本地文件中
         */

        generateKeyToFile(algorithm, "g.pub", "g.pri");
        PublicKey publicKey = getPublicKey("g.pub", algorithm);
        PrivateKey privateKey = getPrivateKey("g.pri", algorithm);

        String encryptMsg = encryptRSA(msg, algorithm, publicKey);
        System.out.println("密文:" + encryptMsg);
        String decryptMsg = decryptRSA(encryptMsg, algorithm, privateKey);
        System.out.println("明文:" + decryptMsg);

    }

    /**
     * 获取公钥
     *
     * @param pubPath   公钥路径
     * @param algorithm 算法
     * @return 返回公钥key对象
     */
    public static PublicKey getPublicKey(String pubPath, String algorithm) throws Exception {
        String publicKeyString = FileUtils.readFileToString(new File(pubPath), Charset.defaultCharset());
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 读取私钥
     *
     * @param priPath   私钥路径
     * @param algorithm 算法
     * @return 返回私钥key对象
     */
    public static PrivateKey getPrivateKey(String priPath, String algorithm) throws Exception {
        String privateKeyString = FileUtils.readFileToString(new File(priPath), Charset.defaultCharset());
        //创建Key工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        //创建私钥key规则
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));
        return keyFactory.generatePrivate(keySpec);
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
     * 生成公私钥
     *
     * @param algorithm 算法
     * @param pubPath   公钥路径
     * @param priPath   私钥路径
     * @throws Exception
     */
    public static void generateKeyToFile(String algorithm, String pubPath, String priPath) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        /**
         * 获取密钥对
         */
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        byte[] privateKeyEncoded = privateKey.getEncoded();
        byte[] publicKeyEncoded = publicKey.getEncoded();
        /**
         * 保存公钥,私钥
         */
        FileUtils.writeStringToFile(new File(pubPath), Base64.encode(publicKeyEncoded), Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath), Base64.encode(privateKeyEncoded), Charset.forName("UTF-8"));
    }
}
