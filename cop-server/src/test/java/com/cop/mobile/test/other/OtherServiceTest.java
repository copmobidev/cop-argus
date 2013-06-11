package com.cop.mobile.test.other;

import java.util.Date;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.common.Result;
import com.cop.mobi.common.UserAgent;
import com.cop.mobi.other.entity.Feedback;
import com.cop.mobi.other.service.OtherService;
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;
import com.cop.mobi.rest.core.UserAgentUtil;
import com.cop.mobile.test.BaseTest;

/**
 * 
 * @author chris.liu
 * 
 */
public class OtherServiceTest extends BaseTest {

	@Autowired
	private OtherService otherService;

	@Test
	public void configTest() {
		String userAgent = "mapi 1.0 peseus 1.0.0 motorola MB525 Android 2.3.5";
		UserAgent ua = UserAgentUtil.parseUserAgent(userAgent);
		String token = "64ac15fa75ccaa3483bf5d04fe2875dae4c4b5f50e19d1db5e193c1534986d7b";
		Token tk = TokenUtil.parseToken(token);
		Result result = otherService.getConfig(ua, tk);
		if (result != null) {
			try {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("result is null");
		}
	}

	@Test
	public void feedbackTest() {
		String userAgent = "mapi 1.0 peseus 1.0.0 motorola MB526 Android 2.3.5";
		String token = "64ac15fa75ccaa3483bf5d04fe2875dae4c4b5f50e19d1db5e193c1534986d7b";
		Token tk = TokenUtil.parseToken(token);
		String content = "不错，加油，希望更好";
		long addtime = new Date().getTime();
		Feedback feedback = new Feedback(tk.getUid(), tk.getMcid(), userAgent,
				content, addtime);
		Result result = otherService.feedback(tk, feedback);

		if (result != null) {
			try {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("result is null");
		}
	}
}
