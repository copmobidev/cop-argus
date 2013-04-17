package com.cop.mobile.test.oilbill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.mycar.entity.OilBill;
import com.cop.mobi.mycar.service.dao.OilBillDao;
import com.cop.mobile.test.BaseTest;

/**
 * 
 * @author chris.liu
 * 
 */
public class OilBillDaoTest extends BaseTest {

	@Autowired
	private OilBillDao oilBillDao;

	@Test
	public void getOilBillTest() {
		try {
			OilBill bill = oilBillDao.getOilBillById(2);
			if (bill != null) {
				System.out.println(bill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getOilBillsTest() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long beginTime = sdf.parse("2012-01-02 00:00:00").getTime();
			long endTime = sdf.parse("2013-06-03 00:00:00").getTime();
			List<OilBill> bills = oilBillDao.getOilBills(1, beginTime, endTime);
			if (bills != null && bills.size() > 0) {
				for (OilBill bill : bills) {
					System.out.println(bill.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateOilBillTest() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long addtime = sdf.parse("2012-01-02 00:00:00").getTime();
			OilBill bill = new OilBill();
			bill.setId(8);
			bill.setUid(1);
			bill.setOil(25.0);
			bill.setUnitprice(8.73);
			bill.setAddtime(addtime);

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

			Object result = oilBillDao.updateOilBill(bill.getId(), updateCols);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteOilBillTest() {
		try {
			int bid = 6;
			Object result = oilBillDao.deleteOilBill(bid);
			if ((Integer) result == 1) {
				System.out.println("delete oil bill successfully");
			} else {
				System.out.println("fail to delete oil bill");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
