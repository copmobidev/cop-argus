package com.cop.mobi.common;

/**
 * 
 * @author chris.liu
 * 
 */
public class UserAgent {
	private String protcolVersion;
	private MobiClient mobiClient;

	public UserAgent(String protocolVersion, MobiClient mobiClient) {
		this.protcolVersion = protocolVersion;
		this.mobiClient = mobiClient;
	}

	public String getProtcolVersion() {
		return protcolVersion;
	}

	public void setProtcolVersion(String protcolVersion) {
		this.protcolVersion = protcolVersion;
	}

	public MobiClient getMobiClient() {
		return mobiClient;
	}

	public void setMobiClient(MobiClient mobiClient) {
		this.mobiClient = mobiClient;
	}

	@Override
	public String toString() {
		return String.format("mapi %s %s", protcolVersion, mobiClient);
	}
}
