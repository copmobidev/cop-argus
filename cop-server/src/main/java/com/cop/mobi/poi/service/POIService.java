package com.cop.mobi.poi.service;

import java.util.List;

import com.cop.mobi.common.Result;
import com.cop.mobi.poi.entity.FuelStation;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public interface POIService {

	/**
	 * 根据所属区域ID获取区域列表
	 * 
	 * @param parentId
	 * @return
	 */
	Result geoAreaList(int parentId);

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
	Result localFuelStation(double lat, double lng, double range);

	/**
	 * 寻找附近加油站
	 * 
	 * @param lat
	 * @param lng
	 * @return
	 */
	List<FuelStation> nearbyFuelStation(double lat, double lng, double range);

}
