package com.cop.mobi.account.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class UserPo {
	private Integer id;
	private String obd; // OBD硬件序列号
	private String email; // 邮箱
	private String name; // 用户名
	private String pwd; // 密码
	private Integer sex; // 0--man; 1--female
	private String profile; // 头像文件名
	private Long registerTime; // 注册时间

	public UserPo() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Long registerTime) {
		this.registerTime = registerTime;
	}

}
