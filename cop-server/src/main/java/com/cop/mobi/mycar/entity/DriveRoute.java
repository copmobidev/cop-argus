package com.cop.mobi.mycar.entity;

import java.util.List;

/**
 * 
 * @author chris.liu
 * 
 */
public class DriveRoute {
	private DriveSummary summary; // 行程摘要
	private List<DrivingPiece> detail; // 行程具体信息

	public DriveRoute(DriveSummary summary, List<DrivingPiece> detail) {
		this.summary = summary;
		this.detail = detail;
	}

	public DriveSummary getSummary() {
		return summary;
	}

	public void setSummary(DriveSummary summary) {
		this.summary = summary;
	}

	public List<DrivingPiece> getDetail() {
		return detail;
	}

	public void setDetail(List<DrivingPiece> detail) {
		this.detail = detail;
	}
}
