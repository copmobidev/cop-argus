package com.cop.mobile.test.account;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.account.entity.UserPo;
import com.cop.mobi.account.service.AccountService;
import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.MyCarPo;
import com.cop.mobile.test.BaseTest;

/**
 * 
 * @author chris.liu
 * 
 */
public class AccountServiceTest extends BaseTest {

	@Autowired
	private AccountService accountService;

	@Test
	public void loginSuccessTest() {

	}

	@Test
	public void loginFailTest() {

	}

	@Test
	public void registerSuccessTest() {
		UserPo registerUser = new UserPo();
		registerUser.setName("test");
		registerUser.setEmail("test@gmail.com");
		registerUser.setPwd("111");
		registerUser.setSex(0);
		MyCarPo registerCar = new MyCarPo();
		registerCar.setSid("E20A39F4-73F5-4BC4-A12F-17D1AD20");
		Result result = accountService.register(registerUser, registerCar);
		if (result != null) {
			try {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}