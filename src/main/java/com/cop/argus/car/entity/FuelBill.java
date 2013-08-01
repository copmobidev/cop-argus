package com.cop.argus.car.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author chris.liu
 * 
 */
public class FuelBill {

	@Expose
	private int id;

	@Expose
	private int fuel;

	@Expose
	private double unitprice;

	@Expose
	private int fuelType;

	@Expose
	private GasStationPoi gasStationPoi;

	@Expose
	private long addtime;

	public FuelBill() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFuel() {
		return fuel;
	}

	public void setFuel(int fuel) {
		this.fuel = fuel;
	}

	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}

	public int getFuelType() {
		return fuelType;
	}

	public void setFuelType(int fuelType) {
		this.fuelType = fuelType;
	}

	public GasStationPoi getGasStationPoi() {
		return gasStationPoi;
	}

	public void setGasStationPoi(GasStationPoi gasStationPoi) {
		this.gasStationPoi = gasStationPoi;
	}

	public long getAddtime() {
		return addtime;
	}

	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}
}
