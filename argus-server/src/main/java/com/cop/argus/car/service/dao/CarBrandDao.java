package com.cop.argus.car.service.dao;

import java.util.List;

import com.cop.argus.car.service.entity.CarBrandPo;

/**
 * 
 * @author chris.liu
 * 
 */
public interface CarBrandDao {

	/**
	 * 获取所有汽车品牌
	 * 
	 * @return
	 */
	List<CarBrandPo> getAllCarBrands();
}
