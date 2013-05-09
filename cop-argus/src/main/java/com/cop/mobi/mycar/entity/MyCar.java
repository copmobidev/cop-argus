package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class MyCar {
	private Integer id;
	private Integer uid;
	private String sid;
	private CarBrand carBrand;

	public MyCar() {

	}

	public MyCar(int id, int uid, String sid, CarBrand carBrand) {
		this.id = id;
		this.uid = uid;
		this.sid = sid;
		this.carBrand = carBrand;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public CarBrand getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(CarBrand carBrand) {
		this.carBrand = carBrand;
	}

	@Override
	public String toString() {
		return String.format(
				"{\"id\":%d,\"uid\":%d,\"sid\":\"%s\",\"brand\":%s}", id, uid,
				sid, carBrand);
	}

	public String toLCString() {
		return String.format("%s|%s", sid, carBrand.toLCString());
	}
}
