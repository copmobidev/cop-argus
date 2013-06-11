package com.cop.mobi.mycar.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cop.mobi.common.AbstractService;
import com.cop.mobi.common.Message;
import com.cop.mobi.common.NameValuePair;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.service.DiagnoseService;
import com.cop.mobi.mycar.service.dao.DiagnoseDao;
import com.cop.mobi.rest.core.SpringApplicationContext;
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("diagnoseService")
public class DiagnoseServiceImpl extends AbstractService implements
		DiagnoseService {
	private static final String Tag = "DiagnoseServiceImpl";

	private static final Map<String, String> OBD_CODES = new HashMap<String, String>();

	private static DiagnoseDao diagnoseDao;

	static {
		init();
	}

	private static void init() {
		try {
			diagnoseDao = (DiagnoseDao) SpringApplicationContext
					.getBean("diagnoseDao");

			List<NameValuePair> items = diagnoseDao.getAllDiagnoseItems();
			if (items != null && items.size() > 0) {
				for (NameValuePair pair : items) {
					OBD_CODES.put(pair.getKey().trim(), pair.getValue().trim());
				}
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	public List<NameValuePair> getBaseDiagnoseItems() {
		return null;
	}

	@Override
	public Result diagnose(Token token, String[] codes) {
		List<String> items = new ArrayList<String>();
		for (String key : codes) {
			String val = OBD_CODES.get(key);
			NameValuePair item = new NameValuePair(key, val);
			items.add(String.format("{\"code\":\"%s\",\"desc\":\"%s\"}",
					item.getKey(), item.getValue()));
		}
		if (items.size() == 0) {
			return new Result(ResultStatus.RS_FAIL, new Message("警告",
					"未发现相应诊断码"));
		}
		String strTk = TokenUtil.generateToken(token.getUid(), token.getMcid(),
				0);
		String tmp = String.format("{\"token\":\"%s\",\"codes\":[%s]}", strTk,
				StringUtils.join(items, ","));
		return new Result(ResultStatus.RS_OK, tmp);
	}
}
