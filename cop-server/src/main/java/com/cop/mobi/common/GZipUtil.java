package com.cop.mobi.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 
 * @author chris.liu
 * 
 */
public class GZipUtil {
	private static final int BUFFER_SIZE = 1024;
	private static final String EXT = ".gz";

	public static void compress(InputStream instream, OutputStream outstream)
			throws IOException {
		GZIPOutputStream gzipOutstream = new GZIPOutputStream(outstream);

		byte[] buf = new byte[BUFFER_SIZE];
		int len;
		while ((len = instream.read(buf)) > 0) {
			gzipOutstream.write(buf, 0, len);
		}
		gzipOutstream.finish();
		gzipOutstream.flush();
		gzipOutstream.close();
	}

	public static void decompress(InputStream instream, OutputStream outstream)
			throws IOException {
		GZIPInputStream gzipInstream = new GZIPInputStream(instream);
		int len;
		byte buf[] = new byte[BUFFER_SIZE];
		while ((len = gzipInstream.read(buf)) > 0) {
			outstream.write(buf, 0, len);
		}
		gzipInstream.close();
	}

	public static byte[] compress(byte[] data) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		compress(bais, baos);
		byte[] output = baos.toByteArray();
		baos.flush();
		baos.close();
		bais.close();
		return output;
	}

	public static byte[] decompress(byte[] data) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		decompress(bais, baos);

		data = baos.toByteArray();
		baos.flush();
		baos.close();

		return data;
	}

	public static void compress(File file, boolean delete) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(file.getPath() + EXT);
		compress(fis, fos);
		fis.close();
		fos.flush();
		fos.close();
		if (delete) {
			file.delete();
		}
	}

	public static void decompress(File file, boolean delete) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(file.getPath().replace(EXT,
				""));
		decompress(fis, fos);
		fis.close();
		fos.flush();
		fos.close();
		if (delete) {
			file.delete();
		}
	}

}
