package com.cop.mobile.test.account;

import java.util.Date;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.account.entity.UserPo;
import com.cop.mobi.account.service.AccountService;
import com.cop.mobi.account.service.dao.AccountDao;
import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.CarBrand;
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

	@Autowired
	private AccountDao accountDao;

	@Test
	public void loginSuccessTest() {

	}

	@Test
	public void registerSuccessTest() {
		String obd = "E20A39F4-73F5-4BC4-A12F-17D1AD20";
		String sid = "E20A39F4-73F5-4BC4-A12F-17D1AD20";
		String manufacturer = "大众";
		String brand = "奥迪";
		String model = "A4";
		String engine = "2.0T";
		CarBrand cb = new CarBrand(manufacturer, brand, model, engine);
		Result result = accountService.register(obd, sid, cb,
				new Date().getTime());
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
	public void updateUserInfoTest() {
		String name = "";
		String email = "test11@gmail.com";
		String pwd = "123456";
		
		UserPo userPo = new UserPo();
		userPo.setId(36);
		userPo.setEmail(email);
		userPo.setName(name);
		userPo.setPwd(pwd);
		Result result = accountService.update(userPo, null);
		if (result != null) {
			try {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("no result return");
		}
	}
	
	@Test
	public void updateMyCarInfoTest() {
		MyCarPo myCarPo = new MyCarPo();
		myCarPo.setId(36);
		myCarPo.setBid(1);
		Result result = accountService.update(null, myCarPo);
		if (result != null) {
			try {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("no result return");
		}
	}
}