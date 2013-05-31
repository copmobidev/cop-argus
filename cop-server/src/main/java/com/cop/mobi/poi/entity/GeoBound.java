package com.cop.mobi.poi.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public class GeoBound {
	public int id;
	public double minLat, minLng;
	public double maxLat, maxLng;
	private List<GeoPoint> bound;

	public GeoBound() {
		this.minLat = Double.MAX_VALUE;
		this.minLng = Double.MAX_VALUE;
		this.maxLat = Double.MIN_VALUE;
		this.maxLng = Double.MIN_VALUE;
	}

	public void addGeoBoundPoint(GeoPoint p) {
		if (p == null) {
			return;
		}
		if (bound == null) {
			bound = new ArrayList<GeoPoint>();
		}
		bound.add(p);
		minLat = minLat < p.getLat() ? minLat : p.getLat();
		minLng = minLng < p.getLng() ? minLng : p.getLng();
		maxLat = maxLat > p.getLat() ? maxLat : p.getLat();
		maxLng = maxLng > p.getLng() ? maxLng : p.getLng();
	}

	public boolean contains(double lat, double lng) {
		if (!inRoughBound(lat, lng)) {
			return false;
		}

		int j = 0, cnt = 0;
		int size = bound.size();
		for (int i = 0; i < size; i++) {
			j = (i == size - 1) ? 0 : j + 1;
			if ((bound.get(i).getLng() != bound.get(j).getLng())
					&& (((lng >= bound.get(i).getLng()) && (lng < bound.get(j)
							.getLng())) || ((lng >= bound.get(j).getLng()) && (lng < bound
							.get(i).getLng())))
					&& (lat < (bound.get(j).getLat() - bound.get(i).getLat())
							* (lng - bound.get(i).getLng())
							/ (bound.get(j).getLng() - bound.get(i).getLng())
							+ bound.get(i).getLat()))
				cnt++;
		}
		return (cnt % 2 > 0) ? true : false;

	}

	public boolean contains(GeoPoint p) {
		return contains(p.getLat(), p.getLng());
	}

	private boolean inRoughBound(double lat, double lng) {
		if (lat > maxLat || lat < minLat || lng > maxLng || lng < minLng) {
			return false;
		}
		return true;
	}
}
