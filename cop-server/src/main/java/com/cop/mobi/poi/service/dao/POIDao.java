package com.cop.mobi.poi.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.mobi.poi.entity.FuelStation;
import com.cop.mobi.poi.entity.GeoArea;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public interface POIDao {

	List<GeoArea> getAllGeoArea();

	List<GeoArea> getGeoArea(@Param(value = "parentId") int parentId);

	List<FuelStation> getGasStation(@Param(value = "maxLat") double maxLat,
			@Param(value = "minLat") double minLat,
			@Param(value = "maxLng") double maxLng,
			@Param(value = "minLng") double minLng);
}
