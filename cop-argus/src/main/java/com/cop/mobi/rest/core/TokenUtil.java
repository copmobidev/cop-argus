package com.cop.mobi.rest.core;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author chris.liu
 * 
 */
public class TokenUtil {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static final SecretKeySpec key;
	private static final IvParameterSpec iv;

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	static {
		SecretKeySpec k = null;
		IvParameterSpec v = null;
		try {
			k = new SecretKeySpec("55C930D827BDABFD".getBytes("ASCII"), "AES");
			v = new IvParameterSpec("D7C6F71A12153EE5".getBytes("ASCII"));
		} catch (Exception e) {
		}
		key = k;
		iv = v;
	}

	/** return null if malformed */
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

	/** return 0 if token is not valid */
	public static int getUserId(String token) {
		if (token == null)
			return 0;
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] bytes = parseHexString(token);
			if (bytes == null)
				return 0;
			byte[] decrBuffer = cipher.doFinal(bytes);
			String decrStr = new String(decrBuffer, "ASCII");
			int i = decrStr.indexOf('|');
			if (i < 0)
				return 0;
			String strId = decrStr.substring(0, i);
			return Integer.parseInt(strId);
		} catch (Exception e) {
			return 0;
		}
	}

	public static String getUserToken(int uid) {
		if (uid == 0)
			return "";
		try {
			String str = String.format("%d|%s", uid, sdf.format(new Date()));
			byte[] strBytes = str.getBytes("ASCII");
			byte[] bytes = new byte[32];
			System.arraycopy(strBytes, 0, bytes, 0, strBytes.length);

			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encrBuffer = cipher.doFinal(bytes);
			String hex = byteArrayToHexString(encrBuffer);
			return hex;
		} catch (Exception e) {
			return "";
		}
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
		if (n < 0)
			n = 0x100 + n;
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
}
