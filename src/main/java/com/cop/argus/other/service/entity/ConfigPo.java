package com.cop.argus.other.service.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class ConfigPo {
	private String platform;
	private String name;
	private Object value;

	public ConfigPo() {

	}

	public ConfigPo(String platform, String name, Object value) {
		this.platform = platform;
		this.name = name;
		this.value = value;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
