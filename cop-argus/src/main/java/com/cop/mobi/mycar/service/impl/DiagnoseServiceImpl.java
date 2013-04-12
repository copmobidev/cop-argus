package com.cop.mobi.mycar.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cop.mobi.common.AbstractService;
import com.cop.mobi.common.KeyValuePair;
import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.service.DiagnoseService;
import com.cop.mobi.mycar.service.dao.DiagnoseDao;
import com.cop.mobi.rest.core.SpringApplicationContext;

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
		try {
			diagnoseDao = (DiagnoseDao) SpringApplicationContext
					.getBean("diagnoseDao");

			List<KeyValuePair> items = diagnoseDao.getAllDiagnoseItems();
			if (items != null && items.size() > 0) {
				for (KeyValuePair pair : items) {
					OBD_CODES.put(pair.getKey().trim(), pair.getValue().trim());
				}
			}
		} catch (Exception e) {
			error(Tag, "init error", e);
		}
	}

	@Override
	public Result getAllDiagnoseItems() {
		List<KeyValuePair> items = new ArrayList<KeyValuePair>();
		for (String key : OBD_CODES.keySet()) {
			String val = OBD_CODES.get(key);
			KeyValuePair item = new KeyValuePair(key, val);
			items.add(item);
		}

		if (items.size() == 0) {
			return new Result(ResultStatus.RS_FAIL, new Message("警告",
					"未发现相应诊断码"));
		}
		
		String tmp = String.format("[%s]", StringUtils.join(items, ","));
		return new Result(ResultStatus.RS_OK, tmp);
	}

	public List<KeyValuePair> getBaseDiagnoseItems() {
		return null;
	}

	@Override
	public Result getDiagnoseItems(List<String> codes) {
		List<KeyValuePair> items = new ArrayList<KeyValuePair>();
		for (String key : codes) {
			String val = OBD_CODES.get(key);
			KeyValuePair item = new KeyValuePair(key, val);
			items.add(item);
		}

		if (items.size() == 0) {
			return new Result(ResultStatus.RS_FAIL, new Message("警告",
					"未发现相应诊断码"));
		}
		
		String tmp = String.format("[%s]", StringUtils.join(items, ","));
		return new Result(ResultStatus.RS_OK, tmp);
	}
}
