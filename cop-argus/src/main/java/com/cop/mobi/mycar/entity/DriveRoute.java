package com.cop.mobi.mycar.entity;

import java.util.List;

import com.cop.mobi.mycar.util.DriveRouteDataDecoder;

/**
 * 
 * @author chris.liu
 * 
 */
public class DriveRoute {
	private Integer id;
	private Integer mcid;
	private RouteStartPiece startPiece;
	private List<RouteDrivingPiece> drivingPieces;
	private RouteEndPiece endPiece;
	private Long beginTime;
	private Long endTime;
	private Long addTime;

	public DriveRoute() {

	}

	public DriveRoute(DriveRoutePo drp) throws Exception {
		this.id = drp.getId();
		this.mcid = drp.getMcid();
		this.beginTime = drp.getBeginTime();
		this.endTime = drp.getEndTime();
		DriveRoute dr = DriveRouteDataDecoder.parseRouteData(drp.getRoute());
		this.startPiece = dr.getStartPiece();
		this.drivingPieces = dr.getDrivingPieces();
		this.endPiece = dr.getEndPiece();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMcid() {
		return mcid;
	}

	public void setMcid(Integer mcid) {
		this.mcid = mcid;
	}

	public RouteStartPiece getStartPiece() {
		return startPiece;
	}

	public void setStartPiece(RouteStartPiece startPiece) {
		this.startPiece = startPiece;
	}

	public List<RouteDrivingPiece> getDrivingPieces() {
		return drivingPieces;
	}

	public void setDrivingPieces(List<RouteDrivingPiece> drivingPieces) {
		this.drivingPieces = drivingPieces;
	}

	public RouteEndPiece getEndPiece() {
		return endPiece;
	}

	public void setEndPiece(RouteEndPiece endPiece) {
		this.endPiece = endPiece;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}
}
