package com.cop.mobi.other.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class ConfigItem {
	private int platform;
	private String key;
	private String value;

	public ConfigItem() {

	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
