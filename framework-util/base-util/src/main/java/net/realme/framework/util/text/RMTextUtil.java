package net.realme.framework.util.text;

import org.apache.commons.codec.binary.Hex;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.util.text
 *
 * @author 91000044
 * @date 2018/7/25 19:20
 */
public class RMTextUtil {

    private static final Logger logger = LoggerFactory.getLogger(RMTextUtil.class);

    public static String generateSalt() {
        return BCrypt.gensalt(12);
    }

    /**
     * 加盐生成密码
     *
     * @param password
     * @param salt
     * @return
     */
    public static String encryptPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    /**
     * @param text
     * @param key
     * @return
     */
    public static String hmacHash256(String text, String key, boolean toLowerCase) {
        if (text == null) {
            return null;
        }
        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            sha256Hmac.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
            return Hex.encodeHexString(sha256Hmac.doFinal(text.getBytes()), toLowerCase);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public static String hmacHash256(String text, String key) {
        return hmacHash256(text, key, true);
    }

    /**
     * 获取指定长度的alpha-number字符串
     *
     * @param len
     * @return
     */
    public static String randomAlphaNumeric(int len) {
        char[] ch = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] c = new char[len];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < len; i++) {
            c[i] = ch[random.nextInt(ch.length)];
        }
        return new String(c);
    }

    /**
     * 获取指定长度的小写字母串
     *
     * @param length
     * @return
     */
    public static String getRandomString(Integer length) {
        String str = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            Integer num = random.nextInt(26);
            sb.append(str.charAt(num));
        }
        return sb.toString();
    }

    /**
     * 反解析php字符串
     *
     * @param phpSerializedStr
     * @return
     */
    public static String unserializePhpString(String phpSerializedStr) {
        //前缀是 [s:] 所以从index=2开始
        int index = 2;
        int delimiterPos = phpSerializedStr.indexOf(':', index);
        int strLen = Integer.parseInt(phpSerializedStr.substring(2, delimiterPos));
        index = delimiterPos + 2;

        int utfStrLen = 0;
        int byteCount = 0;
        while (byteCount != strLen) {
            char ch = phpSerializedStr.charAt(index + utfStrLen++);
            if ((ch >= 0x0001) && (ch <= 0x007F)) {
                byteCount++;
            } else if (ch > 0x07FF) {
                byteCount += 3;
            } else {
                byteCount += 2;
            }
        }
        return phpSerializedStr.substring(index, index + utfStrLen);
    }
}
