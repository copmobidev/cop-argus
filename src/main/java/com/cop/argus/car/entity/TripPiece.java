package com.cop.argus.car.entity;

import com.google.gson.annotations.Expose;

/**
 * 行程切片数据，一分钟的行驶数据描述
 * 
 * @author chris.liu
 * 
 */
public class TripPiece {
	@Expose
	private long timestamp; // 时间戳
	@Expose
	private double lat; // 纬度
	@Expose
	private double lng; // 经度
	@Expose
	private int ele; // 海拔
	@Expose
	private int dist; // 里程
	@Expose
	private double fuel; // 该分钟耗油量
	@Expose
	private double avgSPD; // 平均速度
	@Expose
	private double maxSPD; // 最高速度
	@Expose
	private double bstSPD; // 最佳速度
	@Expose
	private double avgFuel; // 平均油耗
	@Expose
	private double bstFuel; // 最低油耗
	@Expose
	private double avgRPM; // 平均转速
	@Expose
	private double maxRPM; // 最大转速
	@Expose
	private double avgCalLoad; // 平均负载
	@Expose
	private double avgCoolTemp; // 平均水箱温度
	@Expose
	private double avgPadPos; // 节气门位置平均值
	@Expose
	private double maxPadPos; // 节气门位置最大值
	@Expose
	private double minPadPos; // 节气门位置最小值
	@Expose
	private double fuelLV; // 油箱存量
	@Expose
	private double acc; // 急加速次数
	@Expose
	private double brk; // 急刹车次数
	@Expose
	private double overSPD; // 超速时间
	@Expose
	private double idleSPD; // 怠速时间
	@Expose
	private double sliding; // 滑行时间
	@Expose
	private double score; // 行程切片得分

	public TripPiece() {

	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
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

	public int getEle() {
		return ele;
	}

	public void setEle(int ele) {
		this.ele = ele;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	public double getAvgSPD() {
		return avgSPD;
	}

	public void setAvgSPD(double avgSPD) {
		this.avgSPD = avgSPD;
	}

	public double getMaxSPD() {
		return maxSPD;
	}

	public void setMaxSPD(double maxSPD) {
		this.maxSPD = maxSPD;
	}

	public double getBstSPD() {
		return bstSPD;
	}

	public void setBstSPD(double bstSPD) {
		this.bstSPD = bstSPD;
	}

	public double getAvgFuel() {
		return avgFuel;
	}

	public void setAvgFuel(double avgFuel) {
		this.avgFuel = avgFuel;
	}

	public double getBstFuel() {
		return bstFuel;
	}

	public void setBstFuel(double bstFuel) {
		this.bstFuel = bstFuel;
	}

	public double getAvgRPM() {
		return avgRPM;
	}

	public void setAvgRPM(double avgRPM) {
		this.avgRPM = avgRPM;
	}

	public double getMaxRPM() {
		return maxRPM;
	}

	public void setMaxRPM(double maxRPM) {
		this.maxRPM = maxRPM;
	}

	public double getAvgCalLoad() {
		return avgCalLoad;
	}

	public void setAvgCalLoad(double avgCalLoad) {
		this.avgCalLoad = avgCalLoad;
	}

	public double getAvgCoolTemp() {
		return avgCoolTemp;
	}

	public void setAvgCoolTemp(double avgCoolTemp) {
		this.avgCoolTemp = avgCoolTemp;
	}

	public double getAvgPadPos() {
		return avgPadPos;
	}

	public void setAvgPadPos(double avgPadPos) {
		this.avgPadPos = avgPadPos;
	}

	public double getMaxPadPos() {
		return maxPadPos;
	}

	public void setMaxPadPos(double maxPadPos) {
		this.maxPadPos = maxPadPos;
	}

	public double getMinPadPos() {
		return minPadPos;
	}

	public void setMinPadPos(double minPadPos) {
		this.minPadPos = minPadPos;
	}

	public double getFuelLV() {
		return fuelLV;
	}

	public void setFuelLV(double fuelLV) {
		this.fuelLV = fuelLV;
	}

	public double getAcc() {
		return acc;
	}

	public void setAcc(double acc) {
		this.acc = acc;
	}

	public double getBrk() {
		return brk;
	}

	public void setBrk(double brk) {
		this.brk = brk;
	}

	public double getOverSPD() {
		return overSPD;
	}

	public void setOverSPD(double overSPD) {
		this.overSPD = overSPD;
	}

	public double getIdleSPD() {
		return idleSPD;
	}

	public void setIdleSPD(double idleSPD) {
		this.idleSPD = idleSPD;
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
}
