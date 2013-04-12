package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class MyCar {
	private int id;
	private int uid;
	private String obd;
	private String nameCH;
	private double price;
	private long buyDate;

	public MyCar() {

	}

	public MyCar(String obd, String nameCH, double price, long buyDate) {
		this.obd = obd;
		this.nameCH = nameCH;
		this.price = price;
		this.buyDate = buyDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getObd() {
		return obd;
	}

	public void setObd(String obd) {
		this.obd = obd;
	}

	public String getNameCH() {
		return nameCH;
	}

	public void setNameCH(String nameCH) {
		this.nameCH = nameCH;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(long buyDate) {
		this.buyDate = buyDate;
	}

	@Override
	public String toString() {
		return String
				.format("{\"id\":%d,\"uid\":%d,\"obd\":\"%s\",\"nameCH\":\"%s\",\"price\":%f,\"buyDate\":%d}",
						id, uid, obd, nameCH, price, buyDate);
	}
}
