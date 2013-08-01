package com.cop.argus.common.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author chris.liu
 * 
 */
public class NameValuePair {
	@Expose
	private String name;
	@Expose
	private String value;

	public NameValuePair() {

	}

	public NameValuePair(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
