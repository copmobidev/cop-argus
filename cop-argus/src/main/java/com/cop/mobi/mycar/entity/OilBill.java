package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class OilBill {
	private Integer id;
	private Integer uid;
	private Double oil;
	private Double unitprice;
	private Long addtime;

	public OilBill() {

	}

	public OilBill(Integer uid, Double oil, Double unitPrice, Long addtime) {
		this.uid = uid;
		this.oil = oil;
		this.unitprice = unitPrice;
		this.addtime = addtime;
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

	public Double getOil() {
		return oil;
	}

	public void setOil(Double oil) {
		this.oil = oil;
	}

	public Double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}

	public Long getAddtime() {
		return addtime;
	}

	public void setAddtime(Long addtime) {
		this.addtime = addtime;
	}

	public String toString() {
		return String
				.format("{\"id\":%d,\"uid\":%d,\"oil\":%f,\"unitprice\":%f,\"addtime\":%d}",
						id, uid, oil, unitprice, addtime);
	}
}
