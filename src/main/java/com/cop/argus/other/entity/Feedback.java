package com.cop.argus.other.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author chris.liu
 * 
 */
public class Feedback {
	@Expose
	private Integer uid;

	@Expose
	private String content;

	@Expose
	private Long addtime;

	public Feedback(Integer uid, String content, Long addtime) {
		this.uid = uid;
		this.content = content;
		this.addtime = addtime;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getAddtime() {
		return addtime;
	}

	public void setAddtime(Long addtime) {
		this.addtime = addtime;
	}

}
