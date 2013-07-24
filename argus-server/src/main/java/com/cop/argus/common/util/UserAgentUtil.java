package com.cop.argus.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cop.argus.common.entity.Mobi;
import com.cop.argus.common.entity.Product;
import com.cop.argus.common.entity.UserAgent;

/**
 * 
 * @author chris.liu
 * 
 */
public class UserAgentUtil {

	protected static final Logger log = Logger.getLogger(UserAgentUtil.class);

	private static final Pattern UA_PESEUS_ANDROID_REGEX = Pattern.compile(
			"mapi\\s*([0-9\\.]+)\\s*\\ peseus ([0-9\\.]+)(.*) (?:.*)",
			Pattern.CASE_INSENSITIVE);

	private static final Pattern UA_ACHILLES_IOS_REGEX = Pattern.compile(
			"mapi\\s*([0-9\\.]+)\\s*\\ achilles ([0-9\\.]+)(.*) (?:.*)",
			Pattern.CASE_INSENSITIVE);
	private static final Pattern UA_ACHILLES_ANDROID_REGEX = Pattern.compile(
			"mapi\\s*([0-9\\.]+)\\s*\\ achilles ([0-9\\.]+)(.*) \\s*(?:.*)",
			Pattern.CASE_INSENSITIVE);

	private static List<Pattern> patterns = new ArrayList<Pattern>();

	static {
		patterns.add(UA_PESEUS_ANDROID_REGEX);
		patterns.add(UA_ACHILLES_IOS_REGEX);
		patterns.add(UA_ACHILLES_ANDROID_REGEX);
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
			for (Pattern pattern : patterns) {
				Matcher matcher = pattern.matcher(ua);
				if (matcher.find() && matcher.groupCount() == 3) {
					String[] tmp = ua.split("\\ ");
					String protocolVersion = tmp[1];
					Mobi mobi = new Mobi(tmp[4], tmp[5], tmp[6], tmp[7]);
					String clientType = tmp[2];
					String clientVersion = tmp[3];
					Product product = new Product(clientType, clientVersion,
							mobi);
					UserAgent userAgent = new UserAgent(protocolVersion,
							product);
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
