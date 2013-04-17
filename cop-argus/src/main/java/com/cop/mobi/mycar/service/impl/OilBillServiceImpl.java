package com.cop.mobi.mycar.service.impl;

import java.util.ArrayList;
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
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	@Override
	public Result addBill(OilBill bill) {
		Result result = null;
		try {
			OilBill existBill = oilBillDao.getOilBillByAddtime(bill.getUid(),
					bill.getAddtime());
			if (existBill != null) {
				result = new Result(ResultStatus.RS_FAIL, new Message("错误",
						"该账单已存在"));
			} else {
				int optCode = oilBillDao.addOilBill(bill);
				if ((Integer) optCode == 1) {
					OilBill addedBill = oilBillDao.getOilBillByAddtime(
							bill.getUid(), bill.getAddtime());
					if (addedBill != null) {
						result = new Result(ResultStatus.RS_OK, addedBill);
					} else {
						result = new Result(ResultStatus.RS_FAIL, new Message(
								"错误", "添加账单失败"));
					}
				} else {
					result = new Result(ResultStatus.RS_FAIL, new Message("错误",
							"添加账单失败"));
				}
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, String.format("addBill(%s)"),
					bill), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result getBills(int uid, long beginTime, long endTime) {
		Result result = null;
		try {
			List<OilBill> bills = oilBillDao.getOilBills(uid, beginTime,
					endTime);
			if (bills == null || bills.size() == 0) {
				result = new Result(ResultStatus.RS_FAIL, new Message("警告",
						"未发现相应账单"));
			}

			String tmp = String.format("[%s]", StringUtils.join(bills, ","));
			result = new Result(ResultStatus.RS_OK, tmp);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, String.format(
					"getBills(%d,%d,%d)", uid, beginTime, endTime)), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result updateBill(OilBill bill) {
		Result result = null;
		try {
			List<String> cols = new ArrayList<String>();
			if (bill.getOil() != null) {
				cols.add(String.format("oil=%f", bill.getOil()));
			}
			if (bill.getUnitprice() != null) {
				cols.add(String.format("unitprice=%f", bill.getUnitprice()));
			}
			if (bill.getAddtime() != null) {
				cols.add(String.format("oil=%d", bill.getAddtime()));
			}
			String updateCols = StringUtils.join(cols, ",");
			int optCode = oilBillDao.updateOilBill(bill.getId(), updateCols);
			if ((Integer) optCode == 1) {
				OilBill updatedBill = oilBillDao.getOilBillById(bill.getId());
				if (updatedBill != null) {
					result = new Result(ResultStatus.RS_OK, updatedBill);
				} else {
					result = new Result(ResultStatus.RS_FAIL, new Message("错误",
							"更新账单失败"));
				}
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("错误",
						"更新账单失败"));
			}
		} catch (Exception e) {
			log.error(
					String.format("%s:%s", Tag,
							String.format("deleteBill() with param:%s", bill)),
					e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result deleteBill(int bid) {
		Result result = null;
		try {
			int optCode = oilBillDao.deleteOilBill(bid);
			if (optCode == 1) {
				result = new Result(ResultStatus.RS_OK, new Message("帳單提示",
						"成功删除该账单"));
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("帳單提示",
						"未能删除该账单"));
			}
		} catch (Exception e) {
			log.error(
					String.format("%s:%s", Tag,
							String.format("deleteBill(%d)", bid)), e);
			result = new Result(ResultStatus.RS_ERROR, new Message("系统错误",
					"服务器内部错误"));
		}
		return result;
	}
}
