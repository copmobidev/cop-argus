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

	int addOilBill(OilBill bill);

	OilBill getOilBillById(@Param(value = "bid") int bid);

	OilBill getOilBillByAddtime(@Param(value = "uid") int uid,
			@Param(value = "addtime") long addtime);

	List<OilBill> getOilBills(@Param(value = "uid") int uid,
			@Param(value = "beginTime") long beginTime,
			@Param(value = "endTime") long endTime);

	int updateOilBill(@Param(value = "bid") int bid,
			@Param(value = "updatecols") String updatecols);

	int deleteOilBill(@Param(value = "bid") int bid);

}
