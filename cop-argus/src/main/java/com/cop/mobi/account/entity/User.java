package com.cop.mobi.account.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class User {
	private Integer id;
	private String email;
	private String name;
	private String pwd;
	private Integer sex; // 0--man; 1--female
	private String profile;

	public User() {

	}

	public User(String email, String name, String pwd, int sex, String profile) {
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.profile = profile;
	}

	public User(UserPo user) {
		id = user.getId();
		email = user.getEmail();
		name = user.getName();
		pwd = user.getPwd();
		sex = user.getSex();
		profile = user.getProfile();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return String
				.format("{\"id\":%d,\"email\":\"%s\",\"name\":\"%s\",\"sex\":%d,\"profile\":\"%s\"}",
						id, email, name, sex, profile);
	}
}
