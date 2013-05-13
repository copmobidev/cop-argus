package com.cop.mobi.common;

/**
 * 
 * @author chris.liu
 * 
 */
public class MobiClient {
	private String phoneType;
	private MobiOS os;

	public MobiClient(String phoneType, MobiOS os) {
		this.phoneType = phoneType;
		this.os = os;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public MobiOS getOs() {
		return os;
	}

	public void setOs(MobiOS os) {
		this.os = os;
	}
}
