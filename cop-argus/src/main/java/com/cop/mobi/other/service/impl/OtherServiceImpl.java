package com.cop.mobi.other.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.cop.mobi.common.AbstractService;
import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.common.UserAgent;
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
public class OtherServiceImpl extends AbstractService implements OtherService {
	private static final String Tag = "MyCarServiceImpl";

	private static Map<String, String> CONFIGS = new HashMap<String, String>();
	private static OtherDao otherDao;

	static {
		init();
	}

	public static void init() {
		try {
			otherDao = (OtherDao) SpringApplicationContext.getBean("otherDao");
			
			
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	@Override
	public Result getConfig(UserAgent ua, Token token) {
		Result result = null;
		try {
			otherDao.getConfig();
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
