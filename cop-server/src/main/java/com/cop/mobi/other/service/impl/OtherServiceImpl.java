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
import com.cop.mobi.rest.core.TokenUtil;

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
		int i = 0, j = 0, k = 0;
		for (i = 0; i < CONFIGS.size(); ++i) {
			if (CONFIGS.get(i).getPlatform() == 0) {
				tmpIOS[j] = String.format("\"%s\":\"%s\"", CONFIGS.get(i)
						.getKey(), CONFIGS.get(i).getValue());
				j++;
			} else if (CONFIGS.get(i).getPlatform() == 1) {
				tmpAndroid[k] = String.format("\"%s\":\"%s\"", CONFIGS.get(i)
						.getKey(), CONFIGS.get(i).getValue());
				k++;
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
			String tmp = null;
			if (ua.getMobiClient().getMobi().getOs() == OS.ANDROID
					&& ANDROID_CONFIG != null) {
				String tk = TokenUtil.generateToken(token.getUid(),
						token.getMcid(), 0);
				tmp = String.format("{\"token\":\"%s\",\"config\":%s}", tk,
						ANDROID_CONFIG);
				result = new Result(ResultStatus.RS_OK, tmp);
			} else if (ua.getMobiClient().getMobi().getOs() == OS.IOS
					&& IOS_CONFIG != null) {
				String tk = TokenUtil.generateToken(token.getUid(),
						token.getMcid(), 0);
				tmp = String.format("{\"token\":\"%s\",\"config\":%s}", tk,
						IOS_CONFIG);
				result = new Result(ResultStatus.RS_OK, tmp);
			} else {
				String tk = TokenUtil.generateToken(token.getUid(),
						token.getMcid(), 0);
				tmp = String.format("{\"token\":\"%s\",\"message\":%s}", tk,
						new Message("配置", "无相关配置").toString());
				result = new Result(ResultStatus.RS_FAIL, tmp);
			}
		} catch (Exception e) {
			log.error("getConfig() error", e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result feedback(Token token, Feedback feedback) {
		Result result = null;
		try {
			int optCode = otherDao.addFeedback(feedback);
			String tk = TokenUtil.generateToken(token.getUid(),
					token.getMcid(), 0);
			if (optCode == 1) {
				String tmp = String.format("{\"token\":\"%s\",\"message\":%s}", tk,
						new Message("反馈", "反馈成功").toString());
				result = new Result(ResultStatus.RS_OK, tmp);
			} else {
				String tmp = String.format("{\"token\":\"%s\",\"message\":%s}", tk,
						new Message("反馈", "反馈成失敗").toString());
				result = new Result(ResultStatus.RS_FAIL, tmp);
			}
		} catch (Exception e) {
			log.error("feedback() error", e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}
}
