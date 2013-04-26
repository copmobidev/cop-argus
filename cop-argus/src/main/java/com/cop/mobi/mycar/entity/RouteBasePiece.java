package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class RouteBasePiece {
	protected int type; // 数据包类型
	protected long time; // 记录时间
	protected double lat; // 纬度
	protected double lng; // 经度
	protected String dir1; // N north, S south
	protected String dir2; // E east, W west
	protected int ele; // 海拔

	public RouteBasePiece(RouteBasePiece basePiece) {
		this.type = basePiece.getType();
		this.time = basePiece.getTime();
		this.lat = basePiece.getLat();
		this.lng = basePiece.getLng();
		this.dir1 = basePiece.getDir1();
		this.dir2 = basePiece.getDir2();
		this.ele = basePiece.getEle();
	}

	public RouteBasePiece(int type, long time, double lat, double lng,
			String dir1, String dir2, int ele) {
		this.type = type;
		this.time = time;
		this.lat = lat;
		this.lng = lng;
		this.dir1 = dir1;
		this.dir2 = dir2;
		this.ele = ele;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getDir1() {
		return dir1;
	}

	public void setDir1(String dir1) {
		this.dir1 = dir1;
	}

	public String getDir2() {
		return dir2;
	}

	public void setDir2(String dir2) {
		this.dir2 = dir2;
	}

	public int getEle() {
		return ele;
	}

	public void setEle(int ele) {
		this.ele = ele;
	}
}
