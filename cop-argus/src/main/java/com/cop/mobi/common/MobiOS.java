package com.cop.mobi.common;

/**
 * 
 * @author chris.liu
 * 
 */
public class MobiOS {
	private OS os;
	private String version;

	public MobiOS(OS os, String version) {
		this.os = os;
		this.version = version;
	}

	public MobiOS(String os, String version) {
		if (os == null) {
			this.os = OS.UNKNOWN;
		} else if ("android".equals(os.toLowerCase())) {
			this.os = OS.ANDROID;
		} else if ("ios".equals(os.toLowerCase())) {
			this.os = OS.IOS;
		} else {
			this.os = OS.UNKNOWN;
		}
		this.version = version;
	}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public static enum OS {
		UNKNOWN, IOS, ANDROID
	}
}
