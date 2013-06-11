package com.cop.mobile.test.fuelbill;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.FuelBill;
import com.cop.mobi.mycar.service.FuelBillService;
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;
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
		String token = "920e9d7c2f12deff1f9d6d7dff51b77c8f44b66560df5a70d787f384eff16904";
		Token tk = TokenUtil.parseToken(token);
		Result result = oilBillService.deleteBill(tk, 5);
		System.out.println(result);
	}

	@Test
	public void updateBillTest() {
		String token = "920e9d7c2f12deff1f9d6d7dff51b77c8f44b66560df5a70d787f384eff16904";
		Token tk = TokenUtil.parseToken(token);
		FuelBill submitBill = new FuelBill();
		submitBill.setId(12);
		submitBill.setUid(2);
		submitBill.setCharge(30.0);
		submitBill.setAddtime(134245325362l);

		Result result = oilBillService.updateBill(tk, submitBill);
		System.out.println(result);
	}
}
