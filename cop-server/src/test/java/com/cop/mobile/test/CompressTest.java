package com.cop.mobile.test;

import java.io.File;

import com.cop.mobi.common.GZipUtil;

/**
 * 测试图片、文件、字符串等的压缩
 * 
 * @author chris.liu
 * 
 */
public class CompressTest {

	/**
	 * 图片压缩测试
	 */
	public static void imgCompressTest() throws Exception {
		File file = new File("tools/data/icon.png");
		GZipUtil.compress(file, false);
	}

	public static void main(String[] args) {
		try {
			imgCompressTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
