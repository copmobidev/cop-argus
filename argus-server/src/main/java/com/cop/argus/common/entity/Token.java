package com.cop.argus.common.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class Token {
	private int uid;
	private long expire;
	private int count;

	public Token(int uid, long expire, int count) {
		this.uid = uid;
		this.expire = expire;
		this.count = count;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
