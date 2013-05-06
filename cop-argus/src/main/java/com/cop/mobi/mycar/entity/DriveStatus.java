package com.cop.mobi.mycar.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class DriveStatus {
	private String status;
	private Long date;

	public DriveStatus(String info) throws Exception {
		String[] tmp = info.split("\\;");
		if (tmp.length == 2) {
			status = tmp[0];
			date = Long.parseLong(tmp[1]);
		} else {
			throw new Exception("invalid data format");
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("{\"status\":\"%s\",\"date\":%d}", status, date);
	}
	
}
