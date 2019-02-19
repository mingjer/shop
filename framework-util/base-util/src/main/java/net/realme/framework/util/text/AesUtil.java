package net.realme.framework.util.text;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author 91000044
 */
public class AesUtil {

    private static Logger logger = LoggerFactory.getLogger(AesUtil.class);

    static final String CIPHER_CBC_PKCS5 = "AES/CBC/PKCS5Padding";
    static final String CIPHER_CTR = "AES/CTR/NoPadding";

    public static String decrypt(String key, byte[] cipherTextBytes, byte[] ivBytes) {
        try {
            CBCBlockCipher aes = new CBCBlockCipher(new AESEngine());
            PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(aes, new PKCS7Padding());
            CipherParameters cipherParameters = new ParametersWithIV(new KeyParameter(key.getBytes(StandardCharsets.UTF_8)), ivBytes);
            cipher.init(false, cipherParameters);
            byte[] buf = new byte[ivBytes.length + cipherTextBytes.length];
            int len = cipher.processBytes(cipherTextBytes, 0, cipherTextBytes.length, buf, 0);
            len += cipher.doFinal(buf, len);

            byte[] output = new byte[len];
            System.arraycopy(buf, 0, output, 0, len);
            return new String(output, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            logger.error("newopposid decryption failed. " , ex);
        }
        return null;
    }

    public static String decrypt(byte[] key, String cipherText, String cipherName) {
        try {
            Cipher cipher = Cipher.getInstance(cipherName);
            byte[] iv = new byte[cipher.getBlockSize()];
            byte[] cipherTextBytes = Base64.getDecoder().decode(cipherText);
            System.arraycopy(cipherTextBytes, 0, iv, 0, iv.length);
            byte[] rawCipherText = new byte[cipherTextBytes.length - iv.length];
            System.arraycopy(cipherTextBytes, iv.length, rawCipherText, 0, rawCipherText.length);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, ivParameterSpec);
            return new String(cipher.doFinal(rawCipherText), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            logger.error("aes decryption failed. {}" , cipherText, ex);
        }
        return null;
    }

    public static String decrypt(String key, String cipherText) {
        return decrypt(key.getBytes(StandardCharsets.UTF_8), cipherText, CIPHER_CBC_PKCS5);
    }

    public static String encrypt(String key, String plainText) {
        return encrypt(key.getBytes(StandardCharsets.UTF_8), plainText, CIPHER_CBC_PKCS5);
    }

    public static String encrypt(byte[] key, String plainText, String cipherName) {
        try {
            Cipher cipher = Cipher.getInstance(cipherName);
            SecureRandom randomSecureRandom =  SecureRandom.getInstance("SHA1PRNG");
            byte[] iv = new byte[cipher.getBlockSize()];
            randomSecureRandom.nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, ivParameterSpec);
            byte[] cipherTextBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            byte[] finalBytes = new byte[iv.length + cipherTextBytes.length];
            System.arraycopy(iv, 0, finalBytes, 0, iv.length);
            System.arraycopy(cipherTextBytes, 0, finalBytes, iv.length, cipherTextBytes.length);
            return Base64.getEncoder().encodeToString(finalBytes);
        } catch (NoSuchAlgorithmException e) {
            logger.error("aes encryption failed. {}", e.getMessage());
        } catch (Exception ex) {
            logger.error("aes encryption failed. ", ex);
        }
        return null;
    }

    public static String ukeyEncrypt(String secret, String plainText) {
        return ukeyEncrypt(secret.getBytes(StandardCharsets.UTF_8), plainText);
    }

    public static String ukeyEncrypt(byte[] secret, String plainText) {
        if (StringUtils.isEmpty(plainText)) {
            return null;
        }
        try {
            byte[] plainTextBytes = plainText.getBytes(StandardCharsets.UTF_8);
            CBCBlockCipher aes = new CBCBlockCipher(new AESEngine());
            PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(aes, new ZeroBytePadding());
            SecureRandom randomSecureRandom =  SecureRandom.getInstance("SHA1PRNG");
            byte[] iv = new byte[cipher.getBlockSize()];
            randomSecureRandom.nextBytes(iv);
            CipherParameters cipherParameters = new ParametersWithIV(new KeyParameter(secret), iv);
            cipher.init(true, cipherParameters);

            byte[] cipherTextBytes = new byte[cipher.getOutputSize(plainTextBytes.length)];
            int len = cipher.processBytes(plainTextBytes, 0, plainTextBytes.length, cipherTextBytes, 0);
            cipher.doFinal(cipherTextBytes, len);

            byte[] output = new byte[iv.length + cipherTextBytes.length];
            System.arraycopy(cipherTextBytes, 0, output, 0, cipherTextBytes.length);
            System.arraycopy(iv, 0, output, cipherTextBytes.length, iv.length);
            return Hex.encodeHexString(output);
        } catch (Exception ex) {
            logger.error("ukey encryption failed. ", ex);
        }
        return null;
    }

    public static String ukeyDecrypt(byte[] secret, String cipherText) {
        if (StringUtils.isEmpty(cipherText)) {
            return null;
        }
        try {
            byte[] cipherTextBytes = Hex.decodeHex(cipherText);
            CBCBlockCipher aes = new CBCBlockCipher(new AESEngine());
            PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(aes, new ZeroBytePadding());
            byte[] iv = new byte[cipher.getBlockSize()];
            System.arraycopy(cipherTextBytes, cipherTextBytes.length - iv.length, iv, 0, iv.length);
            byte[] rawCipherText = new byte[cipherTextBytes.length - iv.length];
            System.arraycopy(cipherTextBytes, 0, rawCipherText, 0, rawCipherText.length);
            CipherParameters cipherParameters = new ParametersWithIV(new KeyParameter(secret), iv);
            cipher.init(false, cipherParameters);

            byte[] buf = new byte[iv.length + cipherTextBytes.length];
            int len = cipher.processBytes(rawCipherText, 0, rawCipherText.length, buf, 0);
            len += cipher.doFinal(buf, len);

            byte[] output = new byte[len];
            System.arraycopy(buf, 0, output, 0, len);
            return new String(output, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            logger.error("ukey decryption failed. {}" , cipherText, ex);
        }
        return null;
    }

    public static String ukeyDecrypt(String secret, String cipherText) {
        return ukeyDecrypt(secret.getBytes(StandardCharsets.UTF_8), cipherText);
    }

    public static void main(String[] args) throws Exception {
        String secret = DigestUtils.md5Hex("%s.&vpe@lx7v/sdf*`izr%1");
        System.out.println(secret);
        String uid = "20507788";
        String cipherText = "16bb043404f85ef2903237d9ee381ed3a5ecb736b54358e39b6ddcea797663b7ce68db68dbda6e0dbfb98816bacd104b";
        System.out.println("cipherText: " + cipherText);
        String plainText = ukeyDecrypt(secret, cipherText);
        System.out.println("plainText: " + plainText);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed("123".getBytes("utf-8"));
        keyGenerator.init(128, random);

        Cipher cipher = Cipher.getInstance("AES");
        byte[] key = keyGenerator.generateKey().getEncoded();
        System.out.println(Base64.getEncoder().encodeToString(key));
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
    }
}
