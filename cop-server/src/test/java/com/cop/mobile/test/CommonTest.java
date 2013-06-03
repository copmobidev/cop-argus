package com.cop.mobile.test;

import org.junit.Test;

import com.cop.mobi.common.UserAgent;
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;
import com.cop.mobi.rest.core.UserAgentUtil;

/**
 * 包括一些基础工具类的UT
 * 
 * @author chris.liu
 * 
 */
public class CommonTest {

	@Test
	public void uaTest() {
		String androidUA = "mapi 1.0 peseus 1.0.0 motorola MB526 Android 2.3.5";
		UserAgent uaAndroid = UserAgentUtil.parseUserAgent(androidUA);
		System.out.println(uaAndroid);

		String iosUA = "MApi 1.0 achilles 1.0.0 IPhone 4S IOS 6.0";
		UserAgent uaIOS = UserAgentUtil.parseUserAgent(iosUA);
		System.out.println(uaIOS);
	}

	@Test
	public void tokenTest() {
		String strToken = TokenUtil.generateToken(1, 1, 3);
		System.out.println(strToken);
		Token token = TokenUtil.parseToken(strToken);
		System.out.println(token);

		strToken = TokenUtil.generateToken(36, 24, 40);
		System.out.println(strToken);
		token = TokenUtil.parseToken(strToken);
		System.out.println(token);
	}
}
