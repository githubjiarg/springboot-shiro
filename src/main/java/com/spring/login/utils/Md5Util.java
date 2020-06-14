package com.spring.login.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Value;

/**
 *  密码加密工具类1
 */
public class Md5Util {

    // 加密类型
    public static final String ENCYPT_TYPE = "MD5";

    // 密码散列次数
    public static final int PASSWORD_HASH_NUM = 100;

    private static String PASSWORD_SALT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    public static String encryptPwd(String password, String salt) {
        Md5Hash md5Hash = new Md5Hash(password, salt, PASSWORD_HASH_NUM);
        return md5Hash.toHex();
    }

    /**
     * 获取密码盐
     * @return
     */
    public static String getSal() {
        String str = "";
        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * PASSWORD_SALT.length());
            str += PASSWORD_SALT.charAt(index);
        }
        return str;
    }

}