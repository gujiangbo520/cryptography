package com.gujiangbo.application.ascill;

/**
 * 字符串转换成Ascii
 */
public class AscillTest02 {
    public static void main(String[] args) {
        String msg = "gujiangbo";
        char[] chars = msg.toCharArray();
        for (char c : chars) {
            int ascill = c;
            System.out.println(ascill);
        }
    }
}
