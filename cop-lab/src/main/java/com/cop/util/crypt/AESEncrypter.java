package com.cop.util.crypt;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public interface AESEncrypter {
	public void encrypt(byte[] in, int length);

	public byte[] getSalt();

	public byte[] getPwVerification();

	public byte[] getFinalAuthentication();
}
