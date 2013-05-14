package com.cop.mobi.other.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class Feedback {
	private int uid;
	private int mcid;
	private String ua;
	private String content;

	public Feedback() {

	}

	public Feedback(int uid, int mcid, String ua, String content) {
		this.uid = uid;
		this.mcid = mcid;
		this.ua = ua;
		this.content = content;
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

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
