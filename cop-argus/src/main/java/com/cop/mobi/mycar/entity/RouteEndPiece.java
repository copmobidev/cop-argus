package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class RouteEndPiece extends RouteBasePiece {
	private int bat;

	public RouteEndPiece(RouteBasePiece basePiece, int bat) {
		super(basePiece);
		this.bat = bat;
	}

	public int getBat() {
		return bat;
	}

	public void setBat(int bat) {
		this.bat = bat;
	}
}
