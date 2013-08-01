package com.cop.argus.car.service.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class GeoAreaPo {
	private int id;
	private String name;
	private int level;
	private int parentId;

	public GeoAreaPo() {

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
}
