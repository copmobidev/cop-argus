package com.cop.mobi.common;

/**
 * 
 * @author chris.liu
 * 
 */
public class Mobi {
	private String phoneType;
	private OS os;
	private String version;

	public Mobi(String phoneType, OS os, String version) {
		this.phoneType = phoneType;
		this.os = os;
		this.version = version;
	}

	public Mobi(String phoneType, String os, String version) {
		this.phoneType = phoneType;
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

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
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

	@Override
	public String toString() {
		return String.format("%s %s %s", phoneType, os, version);
	}
}
