package com.cop.mobi.rest.core;

/**
 * 
 * @author chris.liu
 * 
 */
public class Token {

	private int uid;
	private int mcid;
	private long expiredTime;
	private int count;

	public Token(int uid, int mcid, long expiredTime, int count) {
		this.uid = uid;
		this.mcid = mcid;
		this.expiredTime = expiredTime;
		this.count = count;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getMcid() {
		return mcid;
	}

	public void setMcid(int mcid) {
		this.mcid = mcid;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(long expiredTime) {
		this.expiredTime = expiredTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return String.format(
				"{\"uid\":%d,\"mcid\":%d,\"expiredTime\":%d,\"count\":%d}",
				uid, mcid, expiredTime, count);
	}
}
