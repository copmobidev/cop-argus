package com.cop.util.crypt.impl;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public class AESCryptBase {
	public static final int KEY_SIZE_BIT = 256;

	public static final int KEY_SIZE_BYTE = KEY_SIZE_BIT / 8;

	public static final int ITERATION_COUNT = 1000;

	protected byte[] saltBytes;

	protected byte[] cryptoKeyBytes;

	protected byte[] authenticationCodeBytes;

	protected byte[] pwVerificationBytes;

	protected int blockSize;

	protected int nonce;
}
