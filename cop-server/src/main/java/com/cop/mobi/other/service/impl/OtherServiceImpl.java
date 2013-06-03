package com.cop.mobi.other.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cop.mobi.common.AbstractService;
import com.cop.mobi.common.Message;
import com.cop.mobi.common.Mobi.OS;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.common.UserAgent;
import com.cop.mobi.other.entity.ConfigItem;
import com.cop.mobi.other.entity.Feedback;
import com.cop.mobi.other.service.OtherService;
import com.cop.mobi.other.service.dao.OtherDao;
import com.cop.mobi.rest.core.SpringApplicationContext;
import com.cop.mobi.rest.core.Token;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("otherService")
public class OtherServiceImpl extends AbstractService implements OtherService {
	private static final String Tag = "OtherServiceImpl";

	private static List<ConfigItem> CONFIGS = new ArrayList<ConfigItem>();
	private static String ANDROID_CONFIG = null;
	private static String IOS_CONFIG = null;
	private static OtherDao otherDao;

	static {
		init();
	}

	private static void init() {
		try {
			otherDao = (OtherDao) SpringApplicationContext.getBean("otherDao");
			CONFIGS = otherDao.getConfig();
			if (CONFIGS != null && CONFIGS.size() > 0) {
				formatConfig();
			} else {
				log.info("no config found");
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	private static void formatConfig() {
		String[] tmpIOS = new String[CONFIGS.size() / 2];
		String[] tmpAndroid = new String[CONFIGS.size() / 2];
		for (int i = 0; i < CONFIGS.size(); ++i) {
			if (CONFIGS.get(i).getPlatform() == 0) {
				tmpIOS[i] = String.format("\"%s\":\"%s\"", CONFIGS.get(i)
						.getKey(), CONFIGS.get(i).getValue());
			} else if (CONFIGS.get(i).getPlatform() == 1) {
				tmpAndroid[i] = String.format("\"%s\":\"%s\"", CONFIGS.get(i)
						.getKey(), CONFIGS.get(i).getValue());
			}
		}

		ANDROID_CONFIG = String.format("{%s}",
				StringUtils.join(tmpAndroid, ","));
		IOS_CONFIG = String.format("{%s}", StringUtils.join(tmpIOS, ","));
	}

	@Override
	public Result getConfig(UserAgent ua, Token token) {
		Result result = null;
		try {
			if (ua.getMobiClient().getMobi().getOs() == OS.ANDROID
					&& ANDROID_CONFIG != null) {
				result = new Result(ResultStatus.RS_OK, ANDROID_CONFIG);
			} else if (ua.getMobiClient().getMobi().getOs() == OS.IOS
					&& IOS_CONFIG != null) {
				result = new Result(ResultStatus.RS_OK, IOS_CONFIG);
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("配置",
						"无相关配置"));
			}
		} catch (Exception e) {
			log.error("getConfig() error", e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result feedback(Feedback feedback) {
		Result result = null;
		try {
			int optCode = otherDao.addFeedback(feedback);
			if (optCode == 1) {
				result = new Result(ResultStatus.RS_OK, new Message("反馈",
						"反馈成功"));
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("反馈",
						"反馈成失敗"));
			}
		} catch (Exception e) {
			log.error("feedback() error", e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}
}
