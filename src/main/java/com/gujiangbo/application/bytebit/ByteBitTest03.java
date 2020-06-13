package com.gujiangbo.application.bytebit;

public class ByteBitTest03 {
    public static void main(String[] args) throws Exception {
        String a = "A";
        // 在中文情况下，不同的编码格式，对应不同的字节
        //GBK :编码格式占2个字节
        // UTF-8：编码格式占3个字节
        //byte[] bytes = a.getBytes("GBK");
        byte[] bytes = a.getBytes("UTF-8");
        for (byte b : bytes) {
            System.out.print(b + "   ");
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }
}
