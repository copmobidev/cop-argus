package com.cop.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class DrivingPiece {
	public long timestamp; // 时间戳
	public double lat; // 纬度
	public char dir1; // 方向南北
	public double lng; // 经度
	public char dir2; // 方向东西
	public int ele; // 海拔
	public int maxSPD; // 最高速度
	public int bstFuel; // 最佳油耗
	public int distH; // 里程数H
	public int distL; // 里程数L
	public int avgSPD; // 平均速度
	public int avgRPMH; // 平均转速H
	public int avgRPML; // 平均装素L
	public int avgFuel; // 平均油耗
	public int totalFuel; // 总油耗
	public int calLoad; // 平局负载
	public int coolTemp; // 平均水箱温度
	public int avgPadPos; // 平均节气门位置
	public int maxPadPos; // 最大节气门位置
	public int minPadPos; // 最小节气门位置
	public int fuelLV; // 邮箱存量
	public int acc; // 加速次数
	public int brk; // 刹车次数
	public int overSPD; // 超速时间
	public int idleSPD; // 怠速时间
	public int sliding; // 滑行时间

	@Override
	public String toString() {
		return String
				.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
						timestamp, lat, dir1, lng, dir2, ele, maxSPD, bstFuel,
						distH, distL, avgSPD, avgRPMH, avgRPML, avgFuel,
						totalFuel, calLoad, coolTemp, avgPadPos, maxPadPos,
						minPadPos, fuelLV, acc, brk, overSPD, idleSPD, sliding);
	}
}
