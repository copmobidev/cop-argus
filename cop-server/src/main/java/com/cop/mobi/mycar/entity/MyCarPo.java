package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class MyCarPo {
	private Integer id;
	private String sid; // 车辆序列号
	private Integer uid; // 用户ID
	private Integer bid; // CarBrand ID
	private Integer isBound; // 是否为当前绑定车辆

	public MyCarPo() {

	}

	public MyCarPo(String sid, int uid, int bid, int isBound) {
		this.sid = sid;
		this.uid = uid;
		this.bid = bid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Integer getIsBound() {
		return isBound;
	}

	public void setIsBound(Integer isBound) {
		this.isBound = isBound;
	}
}
