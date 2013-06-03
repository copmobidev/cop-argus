package com.cop.mobi.other.entity;

/**
 * 
 * @author chris.liu
 *
 */
public enum Platform {
	IOS(0),
	Android(1);
	
	private int platform;
	
	private Platform(int platform) {
		this.platform = platform;
	}
	
	public int getPlatform() {
		return platform;
	}
}
