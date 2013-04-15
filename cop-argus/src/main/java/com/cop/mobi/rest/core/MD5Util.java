package com.cop.mobi.rest.core;

import java.security.MessageDigest;

/**
 * 
 * @author chris.liu
 * 
 */
public class MD5Util {

	private static MessageDigest MD5DIGESTER;

	static {
		try {
			MD5DIGESTER = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// MD5加码。32位
	public static String digest(String inStr) {
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}

		return digest(byteArray);
	}

	public static String digest(byte[] data) {
		byte[] md5Bytes = MD5DIGESTER.digest(data);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	// 可逆的加密算法
	public static String KL(String inStr) {
		// String s = new String(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	// 加密后解密
	public static String JM(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String k = new String(a);
		return k;
	}

	// 测试主函数
	public static void main(String args[]) {
		String s = new String("a");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + digest(s));
	}
}
