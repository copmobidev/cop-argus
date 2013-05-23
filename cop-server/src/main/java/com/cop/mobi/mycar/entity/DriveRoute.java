package com.cop.mobi.mycar.entity;

import java.util.List;

/**
 * 
 * @author chris.liu
 * 
 */
public class DriveRoute {
	private DrivingSummary summary; // 行程摘要
	private List<DrivingPiece> detail; // 行程具体信息

	public DriveRoute(DrivingSummary summary, List<DrivingPiece> detail) {
		this.summary = summary;
		this.detail = detail;
	}

	public DrivingSummary getSummary() {
		return summary;
	}

	public void setSummary(DrivingSummary summary) {
		this.summary = summary;
	}

	public List<DrivingPiece> getDetail() {
		return detail;
	}

	public void setDetail(List<DrivingPiece> detail) {
		this.detail = detail;
	}
}
