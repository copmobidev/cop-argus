package com.cop.mobile.test.oilbill;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.FuelBill;
import com.cop.mobi.mycar.service.FuelBillService;
import com.cop.mobile.test.BaseTest;

/**
 * 
 * @author chris.liu
 * 
 */
public class FuelBillServiceTest extends BaseTest {

	@Autowired
	private FuelBillService oilBillService;

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
		FuelBill submitBill = new FuelBill();
		submitBill.setUid(2);
		submitBill.setCharge(30.0);
		submitBill.setAddtime(134245325362l);

		Result result = oilBillService.updateBill(submitBill);
		System.out.println(result);
	}
}
