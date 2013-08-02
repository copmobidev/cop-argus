package com.cop.argus.car.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cop.argus.car.entity.Battery;
import com.cop.argus.car.service.DiagnoseService;
import com.cop.argus.car.service.dao.DiagnoseDao;
import com.cop.argus.common.entity.NameValuePair;
import com.cop.argus.service.common.BasicService;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("diagnoseService")
public class DiagnoseServiceImpl extends BasicService implements
		DiagnoseService {

	private static final Map<String, String> ERR_CODES = new HashMap<String, String>();

	@Autowired
	private DiagnoseDao diagnoseDao;

	@PostConstruct
	public void init() {
		try {
			List<NameValuePair> errCodes = diagnoseDao.getAllErrCode();
			if (errCodes != null && errCodes.size() > 0) {
				for (NameValuePair pair : errCodes) {
					ERR_CODES.put(pair.getName(), pair.getValue());
				}
			} else {
				log.warn("no error code found to do init");
			}
		} catch (Exception e) {
			log.error("diagnose service init error", e);
		}
	}

	@Override
	public List<NameValuePair> diagnose(int uid, List<String> errCodes) {
		List<NameValuePair> result = new ArrayList<NameValuePair>();
		for (String err : errCodes) {
			String desc = ERR_CODES.get(err);
			NameValuePair pair = new NameValuePair(err, desc);
			result.add(pair);
		}

		if (result.size() <= 0) {
			result = null;
		}
		return result;
	}

	@Override
	public List<Battery> battery(int uid) {
		try {
			List<Battery> bats = diagnoseDao.getBattery(uid);
			if (bats != null && bats.size() > 0) {
				return bats;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
