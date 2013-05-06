package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class MyCar {
	private Integer id;
	private String sid;
	private CarBrand carBrand;

	public MyCar() {

	}

	public MyCar(int id, String sid, CarBrand carBrand) {
		this.id = id;
		this.sid = sid;
		this.carBrand = carBrand;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return String.format("{\"id\":%d,\"sid\":\"%s\",\"brand\":%s}", id,
				sid, carBrand);
	}

	public String toLCString() {
		return String.format("%s|%s", sid, carBrand.toLCString());
	}
}
