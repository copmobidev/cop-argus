package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class RouteDrivingPiece extends RouteBasePiece {
	private double avgSpeed; // 平均速度
	private double avgRPMH; // 平均转速MSB
	private double avgRPML; // 平均转速LSB
	private double avgFuelH; // 平均油耗MSB
	private double avgFuelL; // 平均油耗LSB
	private double airTemp; // 进气温度
	private double oilTemp; // 机油温度
	private double avgWTemp; // 平均水箱温度
	private int accBrk; // 急加速/急刹车次数
	private int overSpeed; // 超速/怠速时间
	private int shift; // 完美换挡次数

	public RouteDrivingPiece(RouteBasePiece basePiece, int dist, int avgSpeed,
			int avgRPMH, int avgRPML, int avgFuelH, int avgFuelL, int airTemp,
			int oilTemp, int avgWTemp, int accBrk, int overSpeed, int shift) {
		super(basePiece);
		this.avgSpeed = avgSpeed;
		this.avgRPMH = avgRPMH;
		this.avgRPML = avgRPML;
		this.avgFuelH = avgFuelH;
		this.avgFuelL = avgFuelL;
		this.airTemp = airTemp;
		this.oilTemp = oilTemp;
		this.avgWTemp = avgWTemp;
		this.accBrk = accBrk;
		this.overSpeed = overSpeed;
		this.shift = shift;
	}

	public double getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(double avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public double getAvgRPMH() {
		return avgRPMH;
	}

	public void setAvgRPMH(double avgRPMH) {
		this.avgRPMH = avgRPMH;
	}

	public double getAvgRPML() {
		return avgRPML;
	}

	public void setAvgRPML(double avgRPML) {
		this.avgRPML = avgRPML;
	}

	public double getAvgFuelH() {
		return avgFuelH;
	}

	public void setAvgFuelH(double avgFuelH) {
		this.avgFuelH = avgFuelH;
	}

	public double getAvgFuelL() {
		return avgFuelL;
	}

	public void setAvgFuelL(double avgFuelL) {
		this.avgFuelL = avgFuelL;
	}

	public double getAirTemp() {
		return airTemp;
	}

	public void setAirTemp(double airTemp) {
		this.airTemp = airTemp;
	}

	public double getOilTemp() {
		return oilTemp;
	}

	public void setOilTemp(double oilTemp) {
		this.oilTemp = oilTemp;
	}

	public double getAvgWTemp() {
		return avgWTemp;
	}

	public void setAvgWTemp(double avgWTemp) {
		this.avgWTemp = avgWTemp;
	}

	public int getAccBrk() {
		return accBrk;
	}

	public void setAccBrk(int accBrk) {
		this.accBrk = accBrk;
	}

	public int getOverSpeed() {
		return overSpeed;
	}

	public void setOverSpeed(int overSpeed) {
		this.overSpeed = overSpeed;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

}
