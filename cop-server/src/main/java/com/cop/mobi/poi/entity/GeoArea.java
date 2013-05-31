package com.cop.mobi.poi.entity;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public class GeoArea {
	private int id;
	private String name;
	private int level;
	private int parentId;
	private String bound;
	private double centerLat;
	private double centerLng;

	public GeoArea() {

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public double getCenterLat() {
		return centerLat;
	}

	public void setCenterLat(double centerLat) {
		this.centerLat = centerLat;
	}

	public double getCenterLng() {
		return centerLng;
	}

	public void setCenterLng(double centerLng) {
		this.centerLng = centerLng;
	}

	@Override
	public String toString() {
		return String.format("{\"id\":%d,\"name\":\"%s\",\"parentId\":%d}", id,
				name, parentId);
	}

	public String toLCString() {
		return String.format("\"%s,%s,%s\"", id, name, parentId);
	}
}
