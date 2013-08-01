package com.cop.argus.car.service.entity;

/**
 * POI 索引
 * 
 * @author chris.liu
 * 
 */
public class IdxPoiPo {
	private int id;
	private double lat;
	private double lng;

	public IdxPoiPo() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		String latlng = String.format("%s,%s", lat, lng);
		return latlng.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
