package com.cop.mobi.account.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class User {
	private int id;
	private String obd;
	private String email;
	private String name;
	private Long addtime; // 注册时间

	public User() {

	}

	public User(int id, String obd, String email, String name, long addtime) {
		this.id = id;
		this.obd = obd;
		this.email = email;
		this.name = name;
		this.addtime = addtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObd() {
		return obd;
	}

	public void setObd(String obd) {
		this.obd = obd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAddtime() {
		return addtime;
	}

	public void setAddtime(Long addtime) {
		this.addtime = addtime;
	}

	@Override
	public String toString() {
		return String.format(
				"{\"obd\":\"%s\", \"email\":\"%s\",\"name\":\"%s\"}", obd,
				email, name);
	}

	public String toLCString() {
		return String.format("%s|%s|%s", obd, email, name);
	}
}
