package com.cop.mobi.mycar.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.mobi.mycar.entity.GasStation;

/**
 * 
 * @author chris.liu
 * 
 */
public interface POIDao {

	List<GasStation> getGasStation(@Param(value = "maxLat") double maxLat,
			@Param(value = "minLat") double minLat,
			@Param(value = "maxLng") double maxLng,
			@Param(value = "minLng") double minLng);
}
