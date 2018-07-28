package com.yaozhou.permission.util;

import com.yaozhou.permission.common.security.DESMessageEncrypt;
import com.yaozhou.permission.common.security.MessageEncrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @author Yao.Zhou
 * @since 2018/7/28 18:58
 */
public class EncryptUtil {

    private static final MessageEncrypt MD5 = MessageEncrypt.getInstance("MD5");

    /**
     * MD5计算
     * @param times
     * @param strs
     * @return
     */
    public static String md5(int times, String... strs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strs) {
            stringBuilder.append(str);
        }

        String result = null;
        for (int i = 0; i < times; i++) {
            result = MD5.encode(stringBuilder.toString());
        }

        return result;
    }

    public static final DESMessageEncrypt getDesInstance(String password) {
        return new DESMessageEncrypt(password);
    }

}
