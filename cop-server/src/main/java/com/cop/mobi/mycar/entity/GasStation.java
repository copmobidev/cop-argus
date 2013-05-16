package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class GasStation {
	private int id;
	private String name;
	private String addr;
	private double lat;
	private double lng;
	private int type;
	private int discount;
	private double e0;
	private double e90;
	private double e93;
	private double e97;

	public GasStation() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public double getE0() {
		return e0;
	}

	public void setE0(double e0) {
		this.e0 = e0;
	}

	public double getE90() {
		return e90;
	}

	public void setE90(double e90) {
		this.e90 = e90;
	}

	public double getE93() {
		return e93;
	}

	public void setE93(double e93) {
		this.e93 = e93;
	}

	public double getE97() {
		return e97;
	}

	public void setE97(double e97) {
		this.e97 = e97;
	}

	@Override
	public String toString() {
		return String
				.format("{\"name\":\"%s\",\"addr\":\"%s\",\"lat\":%f,\"lng\":%f,\"type\":%d,\"discount\":%d,\"e0\":%f,\"e90\":%f,\"e93\":%f,\"e97\":%f,}",
						name, addr, lat, lng, type, discount, e0, e90, e93, e97);
	}
}
