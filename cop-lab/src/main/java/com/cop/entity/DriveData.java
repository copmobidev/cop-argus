package com.cop.entity;

import java.util.List;

/**
 * 
 * @author chris.liu
 * 
 */
public class DriveData {
	private DriveSummary summary;
	private List<DrivePiece> pieces;

	public DriveSummary getSummary() {
		return summary;
	}

	public void setSummary(DriveSummary summary) {
		this.summary = summary;
	}

	public List<DrivePiece> getPieces() {
		return pieces;
	}

	public void setPieces(List<DrivePiece> pieces) {
		this.pieces = pieces;
	}
	
	
}
