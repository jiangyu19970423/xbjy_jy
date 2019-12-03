package com.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/03/14:56
 * @Description:
 */
public class MDUtil {
    //盐值
    private static final String SALT = "qwertyuiop";

    public static String md5(String password) {
        String result = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
            md.update((password + SALT).getBytes());
            //加密后的密文(32位),可以多次加密
            result = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;

    }
}
