package com.cop.mobi.mycar.entity;

/**
 * 行程摘要
 * 
 * @author chris.liu
 * 
 */
public class RouteSummary {
	private int jamTime; // 怠速时间比
	private int slowTime; // 低速时间比
	private int highTime; // 高速时间比
	private int overTime; // 超速时间比
	private long time; // 行程时间
	private int dist; // 总行程
	private int brake; // 刹车次数
	private int acc; // 加速次数
	private double totalFuel; // 总油耗
	private double topSpeed; // 最高速度
	private double bstFuel; // 最佳油耗
	private double temp; // 环境温度
	private double bat; // 电池电量
	private String codes; // 错误码
	private double credit; // 行程积分

	public RouteSummary() {

	}

	public int getJamTime() {
		return jamTime;
	}

	public void setJamTime(int jamTime) {
		this.jamTime = jamTime;
	}

	public int getSlowTime() {
		return slowTime;
	}

	public void setSlowTime(int slowTime) {
		this.slowTime = slowTime;
	}

	public int getHighTime() {
		return highTime;
	}

	public void setHighTime(int highTime) {
		this.highTime = highTime;
	}

	public int getOverTime() {
		return overTime;
	}

	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public int getBrake() {
		return brake;
	}

	public void setBrake(int brake) {
		this.brake = brake;
	}

	public int getAcc() {
		return acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}

	public double getTotalFuel() {
		return totalFuel;
	}

	public void setTotalFuel(double totalFuel) {
		this.totalFuel = totalFuel;
	}

	public double getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(double topSpeed) {
		this.topSpeed = topSpeed;
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

	public double getBat() {
		return bat;
	}

	public void setBat(double bat) {
		this.bat = bat;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getCredit() {
		return dist * ((1 + 2 * jamTime) * (1 - 3 * overTime - jamTime))
				- (brake + acc) * 5;
	}

	// 行程平均速度
	public double getAvgSpeed() {
		return dist / time;
	}

	// 行程平均油耗
	public double getAvgFuel() {
		return totalFuel / dist;
	}

}
