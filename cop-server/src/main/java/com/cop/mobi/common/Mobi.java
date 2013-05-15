package com.cop.mobi.common;

/**
 * 
 * @author chris.liu
 * 
 */
public class Mobi {
	private String menufacturer;
	private String model;
	private OS os;
	private String version;

	public Mobi(String menufacturer, String model, OS os, String version) {
		this.menufacturer = menufacturer;
		this.model = model;
		this.os = os;
		this.version = version;
	}

	public Mobi(String menufacturer, String model, String os, String version) {
		this.menufacturer = menufacturer;
		this.model = model;
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

	public String getMenufacturer() {
		return menufacturer;
	}

	public void setMenufacturer(String menufacturer) {
		this.menufacturer = menufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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
		return String.format("%s %s %s %s", menufacturer, model, os, version);
	}
}
