package com.cop.argus.car.service;

import java.util.List;

import com.cop.argus.car.entity.GasStationPoi;

/**
 * 
 * @author chris.liu
 * 
 */
public interface PoiService {

	/**
	 * 获取某个经纬度点给定覆盖范围内的加油站
	 * 
	 * @param lat
	 * @param lng
	 * @param range
	 * @return
	 */
	List<GasStationPoi> gasStationNearby(double lat, double lng, double range);
}
