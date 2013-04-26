package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class RouteStartPiece extends RouteBasePiece {
	private String vin; // 车辆识别号
	private int fuel; // 油量，最高位为是否加油标志
	private int bat; // 电池电量 BAT/10
	private int dist; // 里程表
	private int temp; // 环境温度
	private int cTemp; // 冷却液温度
	private int decodeNum; // 诊断码数量，有则表示后续字节数，无则为

	public RouteStartPiece(RouteBasePiece basePiece, String vin, int fuel,
			int bat, int dist, int temp, int cTemp, int decodeNum) {
		super(basePiece);
		this.vin = vin;
		this.fuel = fuel;
		this.bat = bat;
		this.dist = dist;
		this.temp = temp;
		this.cTemp = cTemp;
		this.decodeNum = decodeNum;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public int getFuel() {
		return fuel;
	}

	public void setFuel(int fuel) {
		this.fuel = fuel;
	}

	public int getBat() {
		return bat;
	}

	public void setBat(int bat) {
		this.bat = bat;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getcTemp() {
		return cTemp;
	}

	public void setcTemp(int cTemp) {
		this.cTemp = cTemp;
	}

	public int getDecodeNum() {
		return decodeNum;
	}

	public void setDecodeNum(int decodeNum) {
		this.decodeNum = decodeNum;
	}

}
