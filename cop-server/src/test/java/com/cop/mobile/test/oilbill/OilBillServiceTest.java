package com.cop.mobile.test.oilbill;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.OilBill;
import com.cop.mobi.mycar.service.OilBillService;
import com.cop.mobile.test.BaseTest;

/**
 * 
 * @author chris.liu
 * 
 */
public class OilBillServiceTest extends BaseTest {

	@Autowired
	private OilBillService oilBillService;

	@Test
	public void getBillTest() {

	}

	@Test
	public void addBillTest() {

	}

	@Test
	public void deleteBillTest() {
		Result result = oilBillService.deleteBill(5);
		System.out.println(result);
	}

	@Test
	public void updateBillTest() {
		OilBill submitBill = new OilBill();
		submitBill.setUid(2);
		submitBill.setOil(30.0);
		submitBill.setAddtime(134245325362l);

		Result result = oilBillService.updateBill(submitBill);
		System.out.println(result);
	}
}
