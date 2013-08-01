package com.cop.argus.car.service.dao;

import java.util.List;

import com.cop.argus.car.service.entity.GasStationPoiPo;
import com.cop.argus.car.service.entity.GeoAreaPo;
import com.cop.argus.car.service.entity.IdxPoiPo;

/**
 * 
 * @author chris.liu
 * 
 */
public interface PoiDao {

	/**
	 * 获取地域层级数据
	 * 
	 * @return
	 */
	List<GeoAreaPo> getAllGeoArea();

	/**
	 * 获取POI数据以建立索引
	 * 
	 * @return
	 */
	List<IdxPoiPo> getAllPoiIdx();

	List<GasStationPoiPo> getPoiByIds(List<Integer> ids);
}