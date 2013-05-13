package com.cop.mobi.mycar.entity;

import java.util.List;

/**
 * 
 * @author chris.liu
 * 
 */
public class DriveRoute {
	private Long totalTime; // 
	private Double airPress; // 
	private Integer fuelLv; // 
	private Integer bat; // 
	private Integer temp; // 
	private Double dist; // 
	private Double maxSpeed; // 
	private Double bstFuelCost; // 
	private Double avgFuelCost; // 
	private Double totalFuelCost; //
	private Integer acc; // 行程中急加速次数
	private Integer brk; // 行程中急刹车次数
	private Double fastTime; // 顺畅行驶时间比例
	private Double slowTime; // 低速行驶时间比例
	private Double jamTime; // 怠速行驶时间比例
	private String errCodes; // 错误码
	private Long beginTime; // 行程起始时间
	private Long endTime; // 行程结束时间
	private List<DrivingPiece> drivingPieces; // 行程具体信息

	public DriveRoute() {

	}

}
