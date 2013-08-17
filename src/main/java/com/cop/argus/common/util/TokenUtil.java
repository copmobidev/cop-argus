package com.cop.argus.common.util;

import java.security.MessageDigest;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

import com.cop.argus.common.entity.Token;

/**
 * @author chris.liu
 */
public class TokenUtil {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static final SecretKeySpec SECRET_SPEC;
    private static final IvParameterSpec IV_SPEC;

    static {
        SecretKeySpec secretSpec = null;
        IvParameterSpec ivSpec = null;
        try {
            secretSpec = new SecretKeySpec(
                    "55C930D827BDABFD".getBytes("ASCII"), "AES");
            ivSpec = new IvParameterSpec("D7C6F71A12153EE5".getBytes("ASCII"));
        } catch (Exception e) {
        }
        SECRET_SPEC = secretSpec;
        IV_SPEC = ivSpec;
    }

    /**
     * return null if malformed
     */
    private static byte[] parseHexString(String hex) {
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0, n = bytes.length; i < n; i++) {
            int b1 = parseHexC(hex.charAt(i * 2));
            if (b1 < 0)
                return null;
            int b2 = parseHexC(hex.charAt(i * 2 + 1));
            if (b2 < 0)
                return null;
            bytes[i] = (byte) ((b1 << 4) | b2);
        }
        return bytes;
    }

    private static int parseHexC(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        return -1;
    }

    /**
     * return 0 if token is not valid
     */
    public static Token parseToken(String token) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, SECRET_SPEC, IV_SPEC);
            byte[] bytes = parseHexString(token);
            if (bytes == null)
                return null;
            byte[] decrBuffer = cipher.doFinal(bytes);
            String decrStr = new String(decrBuffer, "ASCII");
            String[] str = decrStr.split("\\|");
            if (str == null || str.length != 3) {
                return null;
            }
            int uid = Integer.parseInt(str[0].trim());
            long expiredTime = Long.parseLong(str[1].trim());
            int count = Integer.parseInt(str[2].trim());
            return new Token(uid, expiredTime, count);
        } catch (Exception e) {

        }
        return null;
    }

    public static String generateToken(int uid, int count) throws Exception {
        if (uid <= 0)
            return "";

        String str = String
                .format("%d|%s|%d", uid, new Date().getTime(), count);
        byte[] strBytes = str.getBytes("ASCII");
        byte[] bytes = new byte[32];
        System.arraycopy(strBytes, 0, bytes, 0, strBytes.length);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, SECRET_SPEC, IV_SPEC);
        byte[] encrBuffer = cipher.doFinal(bytes);
        String hex = byteArrayToHexString(encrBuffer);
        return hex;
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 0x100 + n;
        }
        int d1 = n >> 4;
        int d2 = n & 0xF;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString
                    .getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    public static boolean isValid(Token token) {
        if (token == null || token.getUid() <= 0) {
            return false;
        }
        return true;
    }
}
