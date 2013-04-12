package com.cop.mobi.account.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class User {
	private int id;
	private String name;
	private String pwd;
	private int sex; // 0--man; 1--female
	private String email;

	public User() {

	}

	public User(String email, String name, String pwd, int sex) {
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
	}

	public User(UserPo user) {
		id = user.getId();
		email = user.getEmail();
		name = user.getName();
		pwd = user.getPwd();
		sex = user.getSex();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return String.format("{\"email\":\"%s\",\"name\":\"%s\",\"sex\":%d}",
				email, name, sex);
	}
}
