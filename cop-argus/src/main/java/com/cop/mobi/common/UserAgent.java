package com.cop.mobi.common;

/**
 * 
 * @author chris.liu
 * 
 */
public class UserAgent {

	private String protcolVersion;
	private String clientVersion;
	private MobiClient mobiClient;

	public UserAgent(String protocolVersion, String clientVersion,
			MobiClient mobiClient) {
		this.protcolVersion = protocolVersion;
		this.clientVersion = clientVersion;
		this.mobiClient = mobiClient;
	}

	public String getProtcolVersion() {
		return protcolVersion;
	}

	public void setProtcolVersion(String protcolVersion) {
		this.protcolVersion = protcolVersion;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public MobiClient getMobiClient() {
		return mobiClient;
	}

	public void setMobiClient(MobiClient mobiClient) {
		this.mobiClient = mobiClient;
	}

}
