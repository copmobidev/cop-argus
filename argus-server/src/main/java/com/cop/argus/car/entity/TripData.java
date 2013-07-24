package com.cop.argus.car.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author chris.liu
 * 
 */
public class TripData {
	private int uid;
	@Expose
	private long beginTime; // 开始时间
	private double beginLat; // 开始纬度
	private double beginLng; // 开始经度
	private char beginDir1, beginDir2;
	private double beginEle; // 行程开始海拔
	@Expose
	private long endTime; // 结束时间
	private double endLat; // 结束纬度
	private double endLng; // 结束经度
	private char endDir1, endDir2;
	private double endEle; // 行程结束海拔
	@Expose
	private double dist; // 行程总里程
	@Expose
	private double fuel; // 行程总耗油量
	private int errDist; // 故障灯亮起后行驶里程数,最大65535
	private int clrDist; // 故障码清除后行驶里程数,最大65535
	@Expose
	private double maxSPD; // 行程最高速度
	@Expose
	private double bstSPD; // 行程最佳速度
	@Expose
	private double avgFuel; // 行程平均油耗
	@Expose
	private double bstFuel; // 行程最佳速度
	private int fuelLV; // 油量，最高位为是否加油标志
	private double lstFuelLV; // 最后一分钟油箱存量
	private int bat; // 电池电量 BAT/10
	private int airPressure; // 环境气压
	@Expose
	private double temp; // 环境温度
	@Expose
	private double avgCoolTemp; // 平均水箱温度
	private double maxCoolTemp; // 最高水箱温度
	private double avgPadPos; // 节气门位置平均值
	private double maxPadPos; // 节气门位置最大值
	private double minPadPos; // 节气门位置最小值
	@Expose
	private double avgRPM; // 平均转速
	private double maxRPM; // 最高转速
	@Expose
	private int acc; // 行程总急加速次数
	@Expose
	private int brk; // 行程总急刹车次数
	@Expose
	private double overSPD; // 行程总超速时间百分比
	@Expose
	private double idleSPD; // 行程总怠速时间百分比
	@Expose
	private double sliding; // 行程总滑行时间百分比
	@Expose
	private double fast; // 本次行程顺畅的时间比率
	@Expose
	private double slow; // 本次行程缓慢的时间比率
	@Expose
	private double jam; // 本次行程堵车的时间比率(包含怠速）
	@Expose
	private String errCodes; // 错误码
	private String minuteData; // 每分钟行程数据
	private int score; // 行程得分

	public TripData() {

	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;

	}

	public double getBeginLat() {
		return beginLat;
	}

	public void setBeginLat(double beginLat) {
		this.beginLat = beginLat;
	}

	public double getBeginLng() {
		return beginLng;
	}

	public void setBeginLng(double beginLng) {
		this.beginLng = beginLng;
	}

	public char getBeginDir1() {
		return beginDir1;
	}

	public void setBeginDir1(char beginDir1) {
		this.beginDir1 = beginDir1;
	}

	public char getBeginDir2() {
		return beginDir2;
	}

	public void setBeginDir2(char beginDir2) {
		this.beginDir2 = beginDir2;
	}

	public double getBeginEle() {
		return beginEle;
	}

	public void setBeginEle(double beginEle) {
		this.beginEle = beginEle;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public double getEndLat() {
		return endLat;
	}

	public void setEndLat(double endLat) {
		this.endLat = endLat;
	}

	public double getEndLng() {
		return endLng;
	}

	public void setEndLng(double endLng) {
		this.endLng = endLng;
	}

	public char getEndDir1() {
		return endDir1;
	}

	public void setEndDir1(char endDir1) {
		this.endDir1 = endDir1;
	}

	public char getEndDir2() {
		return endDir2;
	}

	public void setEndDir2(char endDir2) {
		this.endDir2 = endDir2;
	}

	public double getEndEle() {
		return endEle;
	}

	public void setEndEle(double endEle) {
		this.endEle = endEle;
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

	public int getErrDist() {
		return errDist;
	}

	public void setErrDist(int errDist) {
		this.errDist = errDist;
	}

	public int getClrDist() {
		return clrDist;
	}

	public void setClrDist(int clrDist) {
		this.clrDist = clrDist;
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

	public int getFuelLV() {
		return fuelLV;
	}

	public void setFuelLV(int fuelLV) {
		this.fuelLV = fuelLV;
	}

	public double getLstFuelLV() {
		return lstFuelLV;
	}

	public void setLstFuelLV(double lstFuelLV) {
		this.lstFuelLV = lstFuelLV;
	}

	public int getBat() {
		return bat;
	}

	public void setBat(int bat) {
		this.bat = bat;
	}

	public int getAirPressure() {
		return airPressure;
	}

	public void setAirPressure(int airPressure) {
		this.airPressure = airPressure;
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

	public double getMaxCoolTemp() {
		return maxCoolTemp;
	}

	public void setMaxCoolTemp(double maxCoolTemp) {
		this.maxCoolTemp = maxCoolTemp;
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

	public String getErrCodes() {
		return errCodes;
	}

	public void setErrCodes(String errCodes) {
		this.errCodes = errCodes;
	}

	public String getMinuteData() {
		return minuteData;
	}

	public void setMinuteData(String minuteData) {
		this.minuteData = minuteData;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String toDBString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s,", uid));
		sb.append(String.format("%s,", beginLat));
		sb.append(String.format("%s,", beginLng));
		sb.append(String.format("%s,", beginEle));
		sb.append(String.format("%s,", beginTime));
		sb.append(String.format("%s,", endLat));
		sb.append(String.format("%s,", endLng));
		sb.append(String.format("%s,", endEle));
		sb.append(String.format("%s,", endTime));
		sb.append(String.format("%s,", dist));
		sb.append(String.format("%s,", fuel));
		sb.append(String.format("%s,", errDist));
		sb.append(String.format("%s,", clrDist));
		sb.append(String.format("%s,", maxSPD));
		sb.append(String.format("%s,", bstSPD));
		sb.append(String.format("%s,", avgFuel));
		sb.append(String.format("%s,", bstFuel));
		sb.append(String.format("%s,", fuelLV));
		sb.append(String.format("%s,", lstFuelLV));
		sb.append(String.format("%s,", bat));
		sb.append(String.format("%s,", airPressure));
		sb.append(String.format("%s,", temp));
		sb.append(String.format("%s,", avgCoolTemp));
		sb.append(String.format("%s,", maxCoolTemp));
		sb.append(String.format("%s,", avgPadPos));
		sb.append(String.format("%s,", maxPadPos));
		sb.append(String.format("%s,", minPadPos));
		sb.append(String.format("%s,", avgRPM));
		sb.append(String.format("%s,", maxRPM));
		sb.append(String.format("%s,", acc));
		sb.append(String.format("%s,", brk));
		sb.append(String.format("%s,", overSPD));
		sb.append(String.format("%s,", idleSPD));
		sb.append(String.format("%s,", sliding));
		sb.append(String.format("%s,", fast));
		sb.append(String.format("%s,", slow));
		sb.append(String.format("%s,", jam));
		sb.append(String.format("'%s',", errCodes));
		sb.append(String.format("'%s',", minuteData));
		sb.append(String.format("%s,", score));
		sb.append(String.format("%s", new Date().getTime()));
		return sb.toString();
	}
}
