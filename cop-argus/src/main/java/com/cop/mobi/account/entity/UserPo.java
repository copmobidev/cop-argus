package com.cop.mobi.account.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class UserPo {
	private int id;
	private String obd;
	private String email;
	private String name;
	private String pwd;
	private int sex; // 0--man; 1--female

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
