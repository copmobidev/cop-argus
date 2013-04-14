package com.cop.mobi.mycar.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.mobi.mycar.entity.OilBill;

/**
 * 
 * @author chris.liu
 * 
 */
public interface OilBillDao {

	Object addOilBill(OilBill bill);

	OilBill getOilBill(@Param(value = "addtime") long addtime);

	List<OilBill> getOilBills(@Param(value = "uid") int uid,
			@Param(value = "beginTime") long beginTime,
			@Param(value = "endTime") long endTime);

	Object deleteOilBill(@Param(value = "bid") int bid);

}
