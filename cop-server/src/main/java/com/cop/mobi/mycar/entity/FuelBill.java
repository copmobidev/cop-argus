package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class FuelBill {
	private Integer id;
	private Integer uid;
	private Integer mcid;
	private int fuelType;
	private Double charge;
	private Double unitprice;
	private Double lat;
	private Double lng;
	private Long addtime;

	public FuelBill() {

	}

	public FuelBill(int uid, int mcid, int fuelType, double charge,
			double unitprice, double lat, double lng, long addtime) {
		this.uid = uid;
		this.mcid = mcid;
		this.fuelType = fuelType;
		this.charge = charge;
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

	public Integer getMcid() {
		return mcid;
	}

	public void setMcid(Integer mcid) {
		this.mcid = mcid;
	}

	public int getFuelType() {
		return fuelType;
	}

	public void setFuelType(int fuelType) {
		this.fuelType = fuelType;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
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

	@Override
	public String toString() {
		return String
				.format("{\"id\":%d,\"uid\":%d,\"fuelType\":%d,\"charge\":%f,\"unitprice\":%f,\"lat\":%f,\"lng\":%f,\"addtime\":%d}",
						id, uid, fuelType, charge, unitprice, lat, lng, addtime);
	}

	public String toLCString() {
		return String.format("%s|%s|%s|%s|%s|%s|%s", id, fuelType, charge,
				unitprice, lat, lng, addtime);
	}
}
