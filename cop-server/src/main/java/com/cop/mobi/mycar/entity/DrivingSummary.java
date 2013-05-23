package com.cop.mobi.mycar.entity;

/**
 * 行程摘要
 * 
 * @author chris.liu
 * 
 */
public class DrivingSummary {
	public long timestamp; // 行程时间戳
	public long startTime; // 起始时间
	public double startLat; // 起始纬度
	public char startDir1; // 起始方向（东西）
	public double startLng; // 起始经度
	public char startDir2; // 起始方向（南北）
	public int startEle; // 其实海拔
	public long endTime; // 结束时间
	public double endLat; // 结束纬度
	public char endDir1; // 结束方向（东西）
	public double endLng; // 结束经度
	public char endDir2; // 结束方向（南北）
	public int endEle; // 结束海拔
	public int time; // 总时间
	public int airPressure; // 环境气压
	public int fuelLV; // 油量
	public int bat; // 电池电量
	public int temp; // 环境温度
	public int distH; // 总里程数H
	public int distL; // 总里程数L
	public int maxSPD; // 最大速度
	public int bstFuel; // 最佳速度
	public int avgSPD; // 平均速度
	public int avgFuel; // 平均油耗
	public int totalFuel; // 总油耗
	public int lstFuelLV; // 邮箱存量
	public int acc; // 加速次数
	public int brk; // 刹车次数
	public int overSPD; // 超速时间比
	public int idleSPD; // 怠速时间比
	public int sliding; // 滑行时间比
	public int fastRate; // 畅行时间比
	public int slowRate; // 缓速时间比
	public int jamRate; // 堵车时间比
	public String errorCodes; // 错误码

	@Override
	public String toString() {
		return String
				.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
						startTime, startLat, startDir1, startLng, startDir2,
						startEle, endTime, endLat, endDir1, endLng, endDir2,
						endEle, time, airPressure, fuelLV, bat, temp, distH,
						distL, maxSPD, bstFuel, avgSPD, avgFuel, totalFuel,
						lstFuelLV, acc, brk, overSPD, idleSPD, sliding,
						fastRate, slowRate, jamRate);
	}

	public double getCredit() {
		return distH * ((1 + 2 * jamRate) * (1 - 3 * fastRate - jamRate))
				- (brk + acc) * 5;
	}
}
