package com.cop.mobi.common.crypt;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public interface AESDecrypter {
	public void decrypt(byte[] in, int length);

	public byte[] getFinalAuthentication();
}
