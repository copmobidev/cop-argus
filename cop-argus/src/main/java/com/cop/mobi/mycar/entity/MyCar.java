package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class MyCar {
	private Integer id;
	private Integer uid;
	private String obd;
	private String nameCH;
	private Double price;
	private Long buyDate;

	public MyCar() {

	}

	public MyCar(String obd, String nameCH, double price, long buyDate) {
		this.obd = obd;
		this.nameCH = nameCH;
		this.price = price;
		this.buyDate = buyDate;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Long buyDate) {
		this.buyDate = buyDate;
	}

	@Override
	public String toString() {
		return String
				.format("{\"id\":%d,\"uid\":%d,\"obd\":\"%s\",\"nameCH\":\"%s\",\"price\":%f,\"buyDate\":%d}",
						id, uid, obd, nameCH, price, buyDate);
	}
}
