package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class MyCar {
	private Integer id;
	private Integer uid;
	private String brand; // 汽车品牌
	private String desc; // 基本信息描述
	private Double price;
	private Long buyDate;
	private Integer isBound;

	public MyCar() {

	}

	public MyCar(int uid, String brand, String desc, double price,
			long buyDate, int isBound) {
		this.uid = uid;
		this.brand = brand;
		this.desc = desc;
		this.price = price;
		this.buyDate = buyDate;
		this.isBound = isBound;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	public Integer getIsBound() {
		return isBound;
	}

	public void setIsBound(Integer isBound) {
		this.isBound = isBound;
	}

	@Override
	public String toString() {
		return String
				.format("{\"id\":%d,\"uid\":%d,\"brand\":\"%s\",\"desc\":\"%s\",\"price\":%f,\"buyDate\":%d, \"isBound\":%d}",
						id, uid, brand, desc, price, buyDate, isBound);
	}
}
