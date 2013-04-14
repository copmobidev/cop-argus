package com.cop.mobile.test.account;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.account.entity.User;
import com.cop.mobi.account.service.AccountService;
import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.MyCar;
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
		User loginUser = new User();
		loginUser.setName("chris");
		loginUser.setPwd("111");
		Result result = accountService.login(loginUser);
		if (result != null) {
			try {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void loginFailTest() {
		User loginUser = new User();
		loginUser.setName("chrisliu");
		loginUser.setPwd("1111");
		Result result = accountService.login(loginUser);
		if (result != null) {
			try {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void registerSuccessTest() {
		User registerUser = new User();
		registerUser.setName("test");
		registerUser.setEmail("test@gmail.com");
		registerUser.setPwd("111");
		registerUser.setSex(0);
		MyCar registerCar = new MyCar();
		registerCar.setNameCH("大众宝来1.6T");
		registerCar.setObd("E20A39F4-73F5-4BC4-A12F-17D1AD07A961");
		registerCar.setBuyDate(1362529391000l);
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