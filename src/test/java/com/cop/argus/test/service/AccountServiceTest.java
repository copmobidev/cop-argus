package com.cop.argus.test.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.cop.argus.account.entity.User;
import com.cop.argus.account.service.AccountService;
import com.cop.argus.common.util.DataFormater;
import com.cop.argus.test.BasicTest;

/**
 * 
 * @author chris.liu
 * 
 */
public class AccountServiceTest extends BasicTest {

	@Resource(name = "accountService")
	private AccountService accountService;

	@Test
	public void boundTest() {
		try {
			String obd = "10192261";
			User result = accountService.bound(obd);
			System.out.println(DataFormater.format(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void reboundSuccessTest() {
		try {
			String obd = "10192260";
			String vin = "GWFF3A52AB005542";
			String calid = "B6000207";
			String cid = "123014AC0FA00FA012ED10450FC00FB2";
			User result = accountService.rebound(obd, vin, calid, cid);
			System.out.println(DataFormater.format(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void reboundFailTest() {
		try {
			String obd = "10192260";
			String vin = "GWFF3A52AB005543";
			String calid = "B6000207";
			String cid = "123014AC0FA00FA012ED10450FC00FB2";
			User result = accountService.rebound(obd, vin, calid, cid);
			System.out.println(DataFormater.format(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateTest() {
		try {
			String vin = "GWFF3A52AB005542";
			String calid = "B6000207";
			String cid = "123014AC0FA00FA012ED10450FC00FB2";
			String name = "chris";
			String email = "chris.liu@gmail.com";
			String pwd = null;
			Integer sex = 0;
			Long birth = new Date().getTime();
			User result = accountService.update(1, vin, calid, cid, email,
					name, pwd, sex, birth);
			System.out.println(DataFormater.format(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}