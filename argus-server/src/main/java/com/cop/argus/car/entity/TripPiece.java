package com.cop.argus.car.entity;

/**
 * 行程切片数据，一分钟的行驶数据描述
 * 
 * @author chris.liu
 * 
 */
public class TripPiece {
	private long timestamp; // 时间戳
	private double lat; // 纬度
	private double lng; // 经度
	private char dir1, dir2; //
	private int ele; // 海拔
	private double maxSPD; // 最高速度
	private double bstSPD; // 最佳速度
	private double bstFuel; // 最低油耗
	private int dist; // 里程
	private double avgSPD; // 平均速度
	private double avgRPM; // 平均转速
	private double maxRPM; // 最大转速
	private double totalFuel; // 该分钟耗油量
	private double avgFuel; // 平均油耗
	private double avgCalLoad; // 平均负载
	private double avgCoolTemp; // 平均水箱温度
	private double avgPadPos; // 节气门位置平均值
	private double maxPadPos; // 节气门位置最大值
	private double minPadPos; // 节气门位置最小值
	private double fuelLv; // 油箱存量
	private double acc; // 急加速次数
	private double brk; // 急刹车次数
	private double overSPD; // 超速时间
	private double idleSPD; // 怠速时间
	private double sliding; // 滑行时间

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

	public char getDir1() {
		return dir1;
	}

	public void setDir1(char dir1) {
		this.dir1 = dir1;
	}

	public char getDir2() {
		return dir2;
	}

	public void setDir2(char dir2) {
		this.dir2 = dir2;
	}

	public int getEle() {
		return ele;
	}

	public void setEle(int ele) {
		this.ele = ele;
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

	public double getBstFuel() {
		return bstFuel;
	}

	public void setBstFuel(double bstFuel) {
		this.bstFuel = bstFuel;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public double getAvgSPD() {
		return avgSPD;
	}

	public void setAvgSPD(double avgSPD) {
		this.avgSPD = avgSPD;
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

	public double getTotalFuel() {
		return totalFuel;
	}

	public void setTotalFuel(double totalFuel) {
		this.totalFuel = totalFuel;
	}

	public double getAvgFuel() {
		return avgFuel;
	}

	public void setAvgFuel(double avgFuel) {
		this.avgFuel = avgFuel;
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

	public double getFuelLv() {
		return fuelLv;
	}

	public void setFuelLv(double fuelLv) {
		this.fuelLv = fuelLv;
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
}
