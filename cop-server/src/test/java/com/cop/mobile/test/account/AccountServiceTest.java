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
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;
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
		} else {
			System.err.println("result is null");
		}
	}

	@Test
	public void updateUserInfoTest() {
		String token = "5cfd9bcf47cc6e20173534914b8e08346079bd04850dcbe99ece7ee2fa3da64d";
		String name = "";
		String email = "test11@gmail.com";
		String pwd = "123456";
		Token tk = TokenUtil.parseToken(token);
		UserPo userPo = new UserPo();
		userPo.setId(tk.getUid());
		userPo.setEmail(email);
		userPo.setName(name);
		userPo.setPwd(pwd);
		Result result = accountService.updateUserInfo(tk, userPo);
		if (result != null) {
			try {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("result is null");
		}
	}

	@Test
	public void reboundTest() {
		String obd = "";
		Result result = accountService.rebound(obd);
		if (result != null) {
			try {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("result is null");
		}
	}
}