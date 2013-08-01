package com.cop.argus.other.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.UserAgent;
import com.cop.argus.other.service.OtherService;
import com.cop.argus.other.service.dao.OtherDao;
import com.cop.argus.other.service.entity.ConfigPo;
import com.cop.argus.service.common.BasicService;
import com.cop.argus.service.common.BasicServiceException;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("otherService")
public class OtherServiceImpl extends BasicService implements OtherService {

	private static final Map<String, Object> ANDROID_CONFIGS = new HashMap<String, Object>();
	private static final Map<String, Object> IOS_CONFIGS = new HashMap<String, Object>();

	@Autowired
	private OtherDao otherDao;

	@PostConstruct
	public void init() {
		try {
			List<ConfigPo> configs = otherDao.getAllConfig();
			for (ConfigPo configPo : configs) {
				if ("ios".equalsIgnoreCase(configPo.getPlatform())) {
					IOS_CONFIGS.put(configPo.getName(), configPo.getValue());
				} else if ("android".equalsIgnoreCase(configPo.getPlatform())) {
					ANDROID_CONFIGS
							.put(configPo.getName(), configPo.getValue());
				}
			}
		} catch (Exception e) {
			log.error("other service init error", e);
		}
	}

	@Override
	public Message feedback(String ua, int uid, String content) {
		Message msg = null;
		try {
			int opt = otherDao.addFeedback(ua, uid, content,
					new Date().getTime());
			if (opt != 1) {
				log.info(String.format("feeback opt fail:%s|%s|%s", ua, uid,
						content));
			}
		} catch (Exception e) {
			log.error("add feedback error", e);
		}
		msg = new Message("用户反馈", "亲,谢谢您的反馈，希望在您的帮助下我们对您的生活提供更多更便利的服务");
		return msg;
	}

	@Override
	public Map<String, Object> getConfig(UserAgent ua, int uid)
			throws BasicServiceException {
		if ("android".equalsIgnoreCase(ua.getMobiClient().getMobi().getOs()
				.name())) {
			return ANDROID_CONFIGS;
		} else if ("ios".equalsIgnoreCase(ua.getMobiClient().getMobi().getOs()
				.name())) {
			return IOS_CONFIGS;
		} else {
			throw new BasicServiceException(BasicServiceException.UNKNOW_ERROR);
		}
	}
}
