package com.cop.argus.car.service.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class FuelBillPo {
	private int id;
	private int uid; // 用户ID
	private double fuel; // 加油量
	private double unitprice; // 单价
	private double fuelType; // 油品
	private int pid; // POI ID
	private long addtime;

	public FuelBillPo() {

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

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}

	public double getFuelType() {
		return fuelType;
	}

	public void setFuelType(double fuelType) {
		this.fuelType = fuelType;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public long getAddtime() {
		return addtime;
	}

	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}
}
