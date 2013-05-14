package com.cop.mobi.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * 
 * @author chris.liu
 * 
 */
public class DataFormatter {

	public static byte[] toEnCryptBytes(Object data) throws IOException {
		byte[] encodedBytes;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(data.toString().getBytes());
		baos.flush();
		encodedBytes = baos.toByteArray();
		byte[] compressedBytes = compress(encodedBytes);
//		return EncryptUtil.encrypt(compressedBytes);
		return compressedBytes;
	}
	
	private static byte[] compress(byte[] source) throws IOException {
		try {
			byte[] result = null;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(source);
			gzip.finish();
			gzip.close();
			result = bos.toByteArray();
			bos.close();
			return result;
		} catch (IOException e) {
			// LOG.error("compress ioexception", e);
			throw new IOException("compress ioexception", e);
		}
	}

	public static void main(String[] args) {

		try {
			String str1 = "hello234102你好";
			byte[] b1 = str1.getBytes();
			for (int i = 0; i < b1.length; ++i) {
				System.out.print(b1[i]);
			}
			System.out.println();
			String str2 = new String(b1);
			System.out.println(str1 + "----" + str2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
