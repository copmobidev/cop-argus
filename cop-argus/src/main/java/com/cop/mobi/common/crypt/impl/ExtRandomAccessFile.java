package com.cop.mobi.common.crypt.impl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public class ExtRandomAccessFile {
	protected RandomAccessFile file;

	public ExtRandomAccessFile(File zipFile) throws IOException {
		this.file = new RandomAccessFile(zipFile, "r");
	}

	public void close() throws IOException {
		file.close();
	}

	public int readByteArray(byte[] buffer, int len) throws IOException {
		int read = file.read(buffer, 0, len);
		return read;
	}

	public byte[] readByteArray(long pos, int length) throws IOException {
		byte[] out = new byte[length];
		file.seek(pos);
		file.read(out, 0, length);
		return out;
	}

	public long readLong() throws IOException {
		byte[] b = new byte[8];
		file.read(b, 0, 8);
		long out = ByteArrayHelper.byteArrayToLong(b);
		return out;
	}

	public long readLong(long pos) throws IOException {
		file.seek(pos);
		return readLong();
	}

	public int readInt() throws IOException {
		byte[] b = new byte[4];
		file.read(b, 0, 4);
		int out = ByteArrayHelper.byteArrayToInt(b);
		return out;
	}

	public int readInt(long pos) throws IOException {
		file.seek(pos);
		return readInt();
	}

	public short readShort() throws IOException {
		byte[] b = new byte[2];
		file.read(b, 0, 2);
		short out = ByteArrayHelper.byteArrayToShort(b);
		return out;
	}

	public short readShort(long pos) throws IOException {
		file.seek(pos);
		return readShort();
	}

	public void seek(int pos) throws IOException {
		file.seek(pos);
	}

	public long getFilePointer() throws IOException {
		return file.getFilePointer();
	}
}
