package com.cop.mobile.test;

import org.junit.Test;

import com.cop.mobi.common.UserAgent;
import com.cop.mobi.rest.core.UserAgentParser;

/**
 * 包括一些基础工具类的UT
 * 
 * @author chris.liu
 * 
 */
public class CommonTest {

	@Test
	public void uaTest() {
		String androidUA = "MApi 1.0 com.cop.v1 5.3.0 MotoA953 Android 2.2";
		UserAgent uaAndroid = UserAgentParser.parseUserAgent(androidUA);
		System.out.println(uaAndroid);

		String iosUA = "MApi 1.0 achilles 1.0.0 IPhone4S IOS 6.0";
		UserAgent uaIOS = UserAgentParser.parseUserAgent(iosUA);
		System.out.println(uaIOS);
	}
}
