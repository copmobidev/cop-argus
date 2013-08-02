package com.cop.argus.car.entity;

import com.google.gson.annotations.Expose;

/**
 * 返回给业务层的数据切片
 * 
 * @author chris
 * 
 */
public class DataPiece {
	@Expose
	private long timestamp;
	@Expose
	private double dist; // 切片行程
	@Expose
	private double fuel; // 切片油耗
	@Expose
	private double time; // 切片时间
	@Expose
	private double maxSPD; // 切片最高速度
	@Expose
	private double bstFuel; // 切片最佳油耗
	@Expose
	private double avgCoolTemp; // 切片水箱温度
	@Expose
	private int acc; // 总加速数
	@Expose
	private int brk; // 总刹车数
	@Expose
	private double overSPD; // 超速时间
	@Expose
	private double slowSPD; // 怠速时间
	@Expose
	private double sliding; // 滑行时间
	@Expose
	private double score; // 切片得分

	public DataPiece() {

	}

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

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public void add(DataPiece dataPiece) {
		timestamp = timestamp > dataPiece.getTimestamp() ? dataPiece
				.getTimestamp() : timestamp;
		dist += dataPiece.getDist();
		fuel += dataPiece.getFuel();
		time += dataPiece.getTime();
		maxSPD = maxSPD > dataPiece.getMaxSPD() ? maxSPD : dataPiece
				.getMaxSPD();
		bstFuel = bstFuel > dataPiece.getBstFuel() ? dataPiece.getBstFuel()
				: bstFuel;
		avgCoolTemp = (avgCoolTemp + dataPiece.getAvgCoolTemp()) / 2;
		acc += dataPiece.getAcc();
		brk += dataPiece.getBrk();
		overSPD += dataPiece.getOverSPD();
		slowSPD += dataPiece.getSlowSPD();
		sliding += dataPiece.getSliding();
		score += dataPiece.getScore();
	}
}
