package com.cop.argus.car.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.argus.car.service.entity.FuelBillPo;

/**
 * 
 * @author chris.liu
 * 
 */
public interface FuelBillDao {

	/**
	 * 根据账单添加时间查询账单
	 * 
	 * @param uid
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<FuelBillPo> getFuelBill(@Param(value = "uid") int uid,
			@Param(value = "beginTime") long beginTime,
			@Param(value = "endTime") long endTime);

	/**
	 * 
	 * @param uid
	 *            用户ID
	 * @param fuel
	 *            加油量
	 * @param unitprice
	 *            单价
	 * @param fuelType
	 *            油品
	 * @param pid
	 *            POI ID
	 * @return
	 */
	int addFuelBill(@Param(value = "uid") int uid,
			@Param(value = "pid") int pid, @Param(value = "fuel") double fuel,
			@Param(value = "unitprice") double unitprice,
			@Param(value = "fuelType") int fuelType,
			@Param(value = "addtime") long addtime);

	/**
	 * 更新账单
	 * 
	 * @param id
	 *            账单ID
	 * @param values
	 *            需要更新的数据
	 * @return
	 */
	int updateFuelBill(@Param(value = "id") int id,
			@Param(value = "values") String values);

	/**
	 * 删除账单数据
	 * 
	 * @param id
	 *            账单ID
	 * @return
	 */
	int deleteFuelBill(@Param(value = "id") int id);
}
