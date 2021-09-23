package com.chenxiaolani.mall.util;

import com.chenxiaolani.mall.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具
 */
public class MD5Utils {
    // 加密
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // Constant.salt 增加加密强度
        return Base64.encodeBase64String(md5.digest((strValue + Constant.salt).getBytes()));
    }
}
