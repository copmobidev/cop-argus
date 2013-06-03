package com.cop.mobi.mycar.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.mobi.mycar.entity.FuelBill;

/**
 * 
 * @author chris.liu
 * 
 */
public interface FuelBillDao {

	int addFuelBill(FuelBill bill);

	FuelBill getFuelBillById(@Param(value = "bid") int bid);

	FuelBill getFuelBillByAddtime(@Param(value = "uid") int uid,
			@Param(value = "addtime") long addtime);

	List<FuelBill> getFuelBills(@Param(value = "uid") int uid,
			@Param(value = "beginTime") long beginTime,
			@Param(value = "endTime") long endTime);

	int updateFuelBill(@Param(value = "bid") int bid,
			@Param(value = "updatecols") String updatecols);

	int deleteFuelBill(@Param(value = "bid") int bid);

}
