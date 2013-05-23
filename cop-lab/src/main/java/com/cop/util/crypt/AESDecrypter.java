package com.cop.util.crypt;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public interface AESDecrypter {
	public void decrypt(byte[] in, int length);

	public byte[] getFinalAuthentication();
}
