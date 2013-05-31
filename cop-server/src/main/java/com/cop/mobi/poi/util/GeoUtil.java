package com.cop.mobi.poi.util;

/**
 * 
 * @author chris.liu
 * 
 */
public class GeoUtil {
	// 经纬度小数点后第四位对应m
	public static final double GEO_L = 111000.0;
	public static final double GEO_RADIUS = 6378.137;

	public static double latToRadians(double lat) {
		return lat * Math.PI / 180;
	} // latToRadians

	public static double distanceTo(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = latToRadians(lat1);
		double radLat2 = latToRadians(lat2);
		double a = radLat1 - radLat2;
		double b = latToRadians(lng1) - latToRadians(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * GEO_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	public static double format(double in, int n) {
		double p = Math.pow(10, n);
		return Math.round(in * p) / p;
	} // format
}
