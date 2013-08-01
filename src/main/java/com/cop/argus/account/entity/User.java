package com.cop.argus.account.entity;

import com.cop.argus.car.entity.CarBrand;
import com.google.gson.annotations.Expose;

/**
 * 
 * @author chris.liu
 * 
 */
public class User {
	private int id;
	@Expose
	private String obd; // OBD序列号
	@Expose
	private CarBrand brand; // 车辆品牌
	@Expose
	private String email; // 邮箱
	@Expose
	private String name; // 用户名
	@Expose
	private int sex; // 性别
	@Expose
	private long birth; // 生日
	@Expose
	private String profile; // 头像URL
	@Expose
	private int score; // 得分
	@Expose
	private int level; // 等级
	@Expose
	private long registerTime; // 注册时间

	public User(String obd) {
		this.obd = obd;
	}

	public User(int id, String obd, CarBrand brand, String email, String name,
			int sex, long birth, String profile, int score, int level,
			long registerTime) {
		this.id = id;
		this.obd = obd;
		this.brand = brand;
		this.email = email;
		this.name = name;
		this.sex = sex;
		this.birth = birth;
		this.profile = profile;
		this.score = score;
		this.level = level;
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

	public CarBrand getBrand() {
		return brand;
	}

	public void setBrand(CarBrand brand) {
		this.brand = brand;
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public long getBirth() {
		return birth;
	}

	public void setBirth(long birth) {
		this.birth = birth;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}
}
