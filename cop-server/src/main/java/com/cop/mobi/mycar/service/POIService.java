package com.cop.mobi.mycar.service;

import java.util.List;

import com.cop.mobi.mycar.entity.GasStation;

/**
 * 
 * @author chris.liu
 * 
 */
public interface POIService {

	/**
	 * 获取指定经纬度点附件某范围内的加油站
	 * 
	 * @param lat
	 *            纬度
	 * @param lng
	 *            经度
	 * @param range
	 *            覆盖范围
	 * @return
	 */
	List<GasStation> getGasStation(double lat, double lng, int range);
}
