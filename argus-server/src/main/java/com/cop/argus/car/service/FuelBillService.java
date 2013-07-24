package com.cop.argus.car.service;

import java.util.List;

import com.cop.argus.car.entity.FuelBill;
import com.cop.argus.car.entity.TimeSpan;

/**
 * 
 * @author chris.liu
 * 
 */
public interface FuelBillService {

	/**
	 * 查询用户某段时间内的加油记录
	 * 
	 * @param uid
	 * @param timeSpan
	 * @return
	 */
	List<FuelBill> get(Integer uid, TimeSpan timeSpan);

	/**
	 * 添加加油记录
	 * 
	 * @param uid
	 *            用户id
	 * @param fuel
	 *            加油量
	 * @param unitprice
	 *            单价
	 * @param fuelType
	 *            油品
	 * @param pid
	 *            poi id
	 * @param addtime
	 *            加油时间
	 * @return
	 */
	FuelBill add(Integer uid, Double fuel, Double unitprice, Integer fuelType,
			Integer pid, Long addtime);

	FuelBill update(Integer id, Double fuel, Double unitprice,
			Integer fuelType, Integer pid, Long addtime);

}
