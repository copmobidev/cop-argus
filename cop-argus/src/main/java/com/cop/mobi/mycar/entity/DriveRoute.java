package com.cop.mobi.mycar.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author chris.liu
 * 
 */
public class DriveRoute {
	private int mcid;
	private List<DriveStatus> route;
	private long beginTime;
	private long endTime;

	public DriveRoute(DriveRoutePo drPo) {
		this.mcid = drPo.getMcid();
		this.route = parseStatus(drPo.getRoute());
		this.beginTime = drPo.getBeginTime();
		this.endTime = drPo.getEndTime();
	}

	public int getMcid() {
		return mcid;
	}

	public void setMcid(int mcid) {
		this.mcid = mcid;
	}

	public List<DriveStatus> getRoute() {
		return route;
	}

	public void setRoute(List<DriveStatus> route) {
		this.route = route;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	private List<DriveStatus> parseStatus(String str) {
		try {
			String[] tmp = str.split("\\|");
			if (tmp != null && tmp.length > 0) {
				List<DriveStatus> status = new ArrayList<DriveStatus>();
				for (int i = 0; i < tmp.length; ++i) {
					try {
						DriveStatus ds = new DriveStatus(tmp[i]);
						status.add(ds);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return status;
			}
		} catch (PatternSyntaxException e) {

		}
		return null;
	}

	private String status2JSONArray() {
		if (route != null) {
			String result = StringUtils.join(route, ",");
			return String.format("[%s]", result);
		}
		return "[]";
	}

	@Override
	public String toString() {
		return String.format(
				"{\"mcid\":%d,\"status\":%s,\"beginTime\":%d,\"endTime\":%d}",
				mcid, status2JSONArray(), beginTime, endTime);
	}
}
