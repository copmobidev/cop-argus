package com.cop.argus.car.service;

import com.cop.argus.car.entity.CarBrand;

/**
 * 
 * @author chris.liu
 * 
 */
public interface CarBrandService {

	/**
	 * 根据车辆品牌ID获取品牌信息
	 * 
	 * @param bid
	 * @return
	 */
	CarBrand getCarBrandById(int bid);

}
