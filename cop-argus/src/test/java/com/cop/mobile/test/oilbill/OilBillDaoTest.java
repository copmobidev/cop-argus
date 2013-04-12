package com.cop.mobile.test.oilbill;

import java.text.SimpleDateFormat;
import java.util.List;

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
			long addtime = 1363276800000l;
			OilBill bill = oilBillDao.getOilBill(addtime);
			if (bill != null) {
				System.out.println(bill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void oilBillDaoTest() {
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
}
