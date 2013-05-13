package com.cop.mobi.rest.core;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cop.mobi.common.MobiClient;
import com.cop.mobi.common.MobiOS;
import com.cop.mobi.common.MobiOS.OS;
import com.cop.mobi.common.UserAgent;

/**
 * 
 * @author chris.liu
 * 
 */
public class UserAgentParser {

	protected static final Logger log = Logger.getLogger(UserAgentParser.class);

	private static final Pattern UA_IOS_REGEX = Pattern.compile(
			"mapi\\s*([0-9\\.]+)\\s*\\(achilles ([0-9\\.]+)(.*);(?:.*)\\)",
			Pattern.CASE_INSENSITIVE);
	private static final Pattern UA_ANDROID_REGEX = Pattern
			.compile(
					"mapi\\s*([0-9\\.]+)\\s*\\(com.cop.v1 ([0-9\\.]+)(.*);\\s*(?:.*)\\)",
					Pattern.CASE_INSENSITIVE);

	private static Map<OS, Pattern> patternMap = new HashMap<OS, Pattern>();

	static {
		patternMap.put(OS.ANDROID, UA_ANDROID_REGEX);
		patternMap.put(OS.IOS, UA_IOS_REGEX);
	}

	/**
	 * 判断客户端user agent是否合法
	 * 
	 * @param ua
	 * @return
	 */
	public static UserAgent parseUserAgent(String str) {
		try {
			boolean flag = false;
			String ua = str.toLowerCase();

			String protocolVersion = null;
			String clientVersion = null;
			String phoneType = null;
			for (OS client : patternMap.keySet()) {
				Pattern pattern = patternMap.get(client);
				Matcher matcher = pattern.matcher(ua);
				if (matcher.find() && matcher.groupCount() == 3) {
					protocolVersion = matcher.group(1).trim();
					System.out.println("protocolVersion\t" + protocolVersion);
					clientVersion = matcher.group(2).trim();
					System.out.println("clientVersion\t" + clientVersion);
					String source = matcher.group(3).trim();
					String[] ss = source.split(" ");
					phoneType = ss[0];
					flag = true;
					break;
				}
			}

			if (flag) {
				MobiOS mobiOS = null;
				String[] s = ua.split(";");
				if (s != null && s.length == 2) {
					String tmp = s[1].trim().substring(0,
							s[1].trim().length() - 1);
					String[] t = tmp.split(" ");
					if (t != null && t.length == 2) {
						mobiOS = new MobiOS(t[0], t[1]);
					}
				}
				MobiClient mobiClient = new MobiClient(phoneType, mobiOS);
				UserAgent userAgent = new UserAgent(protocolVersion,
						clientVersion, mobiClient);
				System.out.println(userAgent);
				return userAgent;
			} else {
				final String msg = "clientType unkown, user agent:" + ua;
				log.error(msg);
				return null;
			}
		} catch (Exception e) {
			log.error("parse user-agent error", e);
			return null;
		}
	}

	public static void main(String[] args) {
		String testUA = "MApi 1.0 (com.cop.v1 5.3.0 MotoA953;Android 2.2)";
		UserAgent ua = parseUserAgent(testUA);
		System.out.println(ua.getMobiClient().getPhoneType());
	}
}
