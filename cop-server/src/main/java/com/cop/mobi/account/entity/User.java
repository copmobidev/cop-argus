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
	private Long registerTime; // 注册时间

	public User() {

	}

	public User(int id, String obd, String email, String name, long registerTime) {
		this.id = id;
		this.obd = obd;
		this.email = email;
		this.name = name;
		this.registerTime = registerTime;
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

	public Long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Long registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return String
				.format("{\"obd\":\"%s\", \"email\":\"%s\",\"name\":\"%s\",\"registerTime\":%d}",
						obd, email, name, registerTime);
	}

	public String toLCString() {
		return String.format("%s|%s|%s|%s", obd, email, name, registerTime);
	}
}
