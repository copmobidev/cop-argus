package com.cop.mobi.mycar.service;

import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.FuelBill;
import com.cop.mobi.rest.core.Token;

/**
 * 
 * @author chris.liu
 * 
 */
public interface FuelBillService {

	/**
	 * 新增账单
	 * 
	 * @param bill
	 * @return
	 */
	Result addBill(Token token, FuelBill bill);

	/**
	 * 获取用户某段时间之间的油单
	 * 
	 * @param uid
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	Result getBills(Token token, long beginTime, long endTime);

	/**
	 * 更新账单
	 * 
	 * @param bill
	 * @return
	 */
	Result updateBill(Token token, FuelBill bill);

	/**
	 * 根据账单号删除某一份账单
	 * 
	 * @param bill
	 * @return
	 */
	Result deleteBill(Token token, int bid);
}
