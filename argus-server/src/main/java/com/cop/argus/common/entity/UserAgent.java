package com.cop.argus.common.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class UserAgent {
	private String protcolVersion;
	private Product mobiClient;

	public UserAgent(String protocolVersion, Product mobiClient) {
		this.protcolVersion = protocolVersion;
		this.mobiClient = mobiClient;
	}

	public String getProtcolVersion() {
		return protcolVersion;
	}

	public void setProtcolVersion(String protcolVersion) {
		this.protcolVersion = protcolVersion;
	}

	public Product getMobiClient() {
		return mobiClient;
	}

	public void setMobiClient(Product mobiClient) {
		this.mobiClient = mobiClient;
	}

	@Override
	public String toString() {
		return String.format("mapi %s %s", protcolVersion, mobiClient);
	}
}
