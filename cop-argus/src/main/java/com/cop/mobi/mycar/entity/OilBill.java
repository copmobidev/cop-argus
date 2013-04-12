package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class OilBill {
	private int id;
	private int uid;
	private double oil;
	private double cost;
	private long addtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public double getOil() {
		return oil;
	}

	public void setOil(double oil) {
		this.oil = oil;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public long getAddtime() {
		return addtime;
	}

	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}

	public String toString() {
		return String.format(
				"{\"uid\":%d,\"oil\":%f,\"cost\":%f,\"addtime\":%d}", uid, oil,
				cost, addtime);
	}
}
