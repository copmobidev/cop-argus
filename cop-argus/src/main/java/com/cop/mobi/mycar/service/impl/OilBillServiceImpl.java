package com.cop.mobi.mycar.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cop.mobi.common.AbstractService;
import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.OilBill;
import com.cop.mobi.mycar.service.OilBillService;
import com.cop.mobi.mycar.service.dao.OilBillDao;
import com.cop.mobi.rest.core.SpringApplicationContext;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("oilBillService")
public class OilBillServiceImpl extends AbstractService implements
		OilBillService {

	private static final String Tag = "OilBillServiceImpl";

	private static OilBillDao oilBillDao;

	static {
		try {
			oilBillDao = (OilBillDao) SpringApplicationContext
					.getBean("oilBillDao");
		} catch (Exception e) {
			error(Tag, "init error", e);
		}
	}

	@Override
	public Result addBill(OilBill bill) {
		try {
			oilBillDao.addOilBill(bill);
			OilBill addedBill = oilBillDao.getOilBill(bill.getAddtime());
			if (addedBill != null) {
				return new Result(ResultStatus.RS_OK, addedBill);
			} else {
				return new Result(ResultStatus.RS_FAIL, new Message("错误",
						"添加账单失败"));
			}
		} catch (Exception e) {
			error(Tag, String.format("addBill(%s)", bill), e);
			return new Result(ResultStatus.RS_FAIL, new Message("错误", "添加账单失败"));
		}
	}

	@Override
	public Result getBills(int uid, long beginTime, long endTime) {
		try {
			List<OilBill> bills = oilBillDao.getOilBills(uid, beginTime,
					endTime);
			if (bills == null || bills.size() == 0) {
				return new Result(ResultStatus.RS_FAIL, new Message("警告",
						"未发现相应账单"));
			}

			String tmp = String.format("[%s]", StringUtils.join(bills, ","));
			return new Result(ResultStatus.RS_OK, tmp);
		} catch (Exception e) {
			error(Tag, String.format("getBills(%d,%d,%d)", uid, beginTime,
					endTime), e);
		}
		return null;
	}

	@Override
	public Result deleteBill(int bid) {
		try {
			Object obj = oilBillDao.deleteOilBill(bid);
			if ((Integer) obj == 1) {
				return new Result(ResultStatus.RS_OK, new Message("帳單提示",
						"成功删除该账单"));
			} else {
				return new Result(ResultStatus.RS_FAIL, new Message("帳單提示",
						"未能删除该账单"));
			}
		} catch (Exception e) {
			error(Tag, String.format("deleteBill(%d)", bid), e);
			return new Result(ResultStatus.RS_ERROR, new Message("系统错误",
					"服务器内部错误"));
		}
	}
}
