package com.cop.mobi.common.crypt.impl;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public interface ZipConstants {
	static long LOCSIG = 0x04034b50L; // "PK\003\004"
	static long EXTSIG = 0x08074b50L; // "PK\007\008"
	static long CENSIG = 0x02014b50L; // "PK\001\002"
	static long ENDSIG = 0x06054b50L; // "PK\005\006"
}
