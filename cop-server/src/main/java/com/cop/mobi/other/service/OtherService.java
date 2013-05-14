package com.cop.mobi.other.service;

import com.cop.mobi.common.Result;
import com.cop.mobi.common.UserAgent;
import com.cop.mobi.other.entity.Feedback;
import com.cop.mobi.rest.core.Token;

/**
 * 
 * @author chris.liu
 * 
 */
public interface OtherService {

	/**
	 * 根据用户user agent和uid mcid获取配置信息
	 * 
	 * @param ua
	 * @param token
	 * @return
	 */
	Result getConfig(UserAgent ua, Token token);

	/**
	 * 用户反馈
	 * 
	 * @param feedback
	 * @return
	 */
	Result feedback(Feedback feedback);

}
