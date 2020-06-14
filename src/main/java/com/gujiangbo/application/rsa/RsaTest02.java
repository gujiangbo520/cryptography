package com.gujiangbo.application.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 非对称加密RSA
 *
 * @author gujiangbo
 */
public class RsaTest02 {

    public static void main(String[] args) throws Exception {
        /**
         * 加密算法
         */
        String algorithm = "RSA";
        /**
         * 创建密钥对生成器对象
         */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        /**
         * 生成密钥对
         */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        /**
         * 生成私钥
         */
        PrivateKey privateKey = keyPair.getPrivate();
        /**
         * 生成公钥
         */
        PublicKey publicKey = keyPair.getPublic();

        /**
         * 获取私钥字节数组
         */
        byte[] privateKeyEncoded = privateKey.getEncoded();

        /**
         * 获取公钥字节数组
         */
        byte[] publicKeyEncoded = publicKey.getEncoded();

        /**
         * 使用Base64对数组进行编码，防止出现乱码
         */
        System.out.println(Base64.encode(privateKeyEncoded));
        System.out.println(Base64.encode(publicKeyEncoded));
    }
}
