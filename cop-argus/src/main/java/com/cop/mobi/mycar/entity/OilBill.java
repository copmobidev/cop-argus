package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class OilBill {
	private Integer id;
	private Integer uid;
	private int oilType;
	private Double oil;
	private Double unitprice;
	private Double lat;
	private Double lng;
	private Long addtime;

	public OilBill() {

	}

	public OilBill(Integer uid, Integer oilType, Double oil, Double unitprice,
			Double lat, Double lng, Long addtime) {
		this.uid = uid;
		this.oilType = oilType;
		this.oil = oil;
		this.unitprice = unitprice;
		this.lat = lat;
		this.lng = lng;
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

	public int getOilType() {
		return oilType;
	}

	public void setOilType(int oilType) {
		this.oilType = oilType;
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

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Long getAddtime() {
		return addtime;
	}

	public void setAddtime(Long addtime) {
		this.addtime = addtime;
	}

	public String toString() {
		return String
				.format("{\"id\":%d,\"uid\":%d,\"oilType\":%d,\"oil\":%f,\"unitprice\":%f,\"lat\":%f,\"lng\":%f,\"addtime\":%d}",
						id, uid, oilType, oil, unitprice, lat, lng, addtime);
	}
}
