package com.cop.argus.car.entity;

import com.google.gson.annotations.Expose;

/**
 * 返回业务层的数据摘要
 * 
 * @author chris
 * 
 */
public class DataSummary {
	@Expose
	private long timestamp; // 摘要数据时间戳
	@Expose
	private double dist; // 总行程
	@Expose
	private double fuel; // 总油耗
	@Expose
	private long time; // 总时间
	@Expose
	private double bstSPD; // 最佳速度
	@Expose
	private double maxSPD; // 最高速度
	@Expose
	private double bstFuel; // 最佳油耗
	@Expose
	private double temp; // 气温
	@Expose
	private double avgCoolTemp; // 平均水箱温度
	@Expose
	private int acc; // 总加速数
	@Expose
	private int brk; // 总刹车数
	@Expose
	private double overSPD;
	@Expose
	private double slowSPD;
	@Expose
	private double sliding;
	@Expose
	private double fast;
	@Expose
	private double slow;
	@Expose
	private double jam;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public double getBstSPD() {
		return bstSPD;
	}

	public void setBstSPD(double bstSPD) {
		this.bstSPD = bstSPD;
	}

	public double getMaxSPD() {
		return maxSPD;
	}

	public void setMaxSPD(double maxSPD) {
		this.maxSPD = maxSPD;
	}

	public double getBstFuel() {
		return bstFuel;
	}

	public void setBstFuel(double bstFuel) {
		this.bstFuel = bstFuel;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getAvgCoolTemp() {
		return avgCoolTemp;
	}

	public void setAvgCoolTemp(double avgCoolTemp) {
		this.avgCoolTemp = avgCoolTemp;
	}

	public int getAcc() {
		return acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}

	public int getBrk() {
		return brk;
	}

	public void setBrk(int brk) {
		this.brk = brk;
	}

	public double getOverSPD() {
		return overSPD;
	}

	public void setOverSPD(double overSPD) {
		this.overSPD = overSPD;
	}

	public double getSlowSPD() {
		return slowSPD;
	}

	public void setSlowSPD(double slowSPD) {
		this.slowSPD = slowSPD;
	}

	public double getSliding() {
		return sliding;
	}

	public void setSliding(double sliding) {
		this.sliding = sliding;
	}

	public double getFast() {
		return fast;
	}

	public void setFast(double fast) {
		this.fast = fast;
	}

	public double getSlow() {
		return slow;
	}

	public void setSlow(double slow) {
		this.slow = slow;
	}

	public double getJam() {
		return jam;
	}

	public void setJam(double jam) {
		this.jam = jam;
	}

	public void add(DataSummary dataSummary) {
		timestamp = timestamp > dataSummary.getTimestamp() ? dataSummary
				.getTimestamp() : timestamp;
		dist += dataSummary.getDist();
		fuel += dataSummary.getFuel();
		maxSPD = maxSPD > dataSummary.getMaxSPD() ? maxSPD : dataSummary
				.getMaxSPD();
		bstFuel = bstFuel > dataSummary.getBstFuel() ? dataSummary.getBstFuel()
				: bstFuel;
		avgCoolTemp = (avgCoolTemp + dataSummary.getAvgCoolTemp()) / 2;
		acc += dataSummary.getAcc();
		brk += dataSummary.getBrk();
		overSPD += dataSummary.getOverSPD();
		slowSPD += dataSummary.getSlowSPD();
		sliding += dataSummary.getSliding();
		fast += dataSummary.getFast();
		slow += dataSummary.getSlow();
		jam += dataSummary.getJam();
	}
}
