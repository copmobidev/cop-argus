package com.cop.argus.common.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class Mobi {
	private String manufacturer; // 制造商
	private String model;// 产品型号
	private OS os; // 操作系统
	private String version; // 系统版本

	public Mobi(String manufacturer, String model, OS os, String version) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.os = os;
		this.version = version;
	}

	public Mobi(String manufacturer, String model, String os, String version) {
		this.manufacturer = manufacturer;
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
		return manufacturer;
	}

	public void setMenufacturer(String menufacturer) {
		this.manufacturer = menufacturer;
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
		return String.format("%s %s %s %s", manufacturer, model, os, version);
	}
}
