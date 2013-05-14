package com.cop.mobi.rest.core;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cop.mobi.common.Mobi;
import com.cop.mobi.common.Mobi.OS;
import com.cop.mobi.common.MobiClient;
import com.cop.mobi.common.UserAgent;

/**
 * 
 * @author chris.liu
 * 
 */
public class UserAgentParser {

	protected static final Logger log = Logger.getLogger(UserAgentParser.class);

	private static final Pattern UA_IOS_REGEX = Pattern.compile(
			"mapi\\s*([0-9\\.]+)\\s*\\ achilles ([0-9\\.]+)(.*) (?:.*)",
			Pattern.CASE_INSENSITIVE);
	private static final Pattern UA_ANDROID_REGEX = Pattern.compile(
			"mapi\\s*([0-9\\.]+)\\s*\\ com.cop.v1 ([0-9\\.]+)(.*) \\s*(?:.*)",
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
			String ua = str.toLowerCase();
			for (OS client : patternMap.keySet()) {
				Pattern pattern = patternMap.get(client);
				Matcher matcher = pattern.matcher(ua);
				if (matcher.find() && matcher.groupCount() == 3) {
					String[] tmp = ua.split("\\ ");
					String protocolVersion = tmp[1];
					Mobi mobi = new Mobi(tmp[4], tmp[5], tmp[6]);
					String clientType = tmp[2];
					String clientVersion = tmp[3];
					MobiClient mobiClient = new MobiClient(clientType,
							clientVersion, mobi);
					UserAgent userAgent = new UserAgent(protocolVersion,
							mobiClient);
					return userAgent;
				}
			}
			return null;
		} catch (Exception e) {
			log.error("parse user-agent error", e);
			return null;
		}
	}
}
