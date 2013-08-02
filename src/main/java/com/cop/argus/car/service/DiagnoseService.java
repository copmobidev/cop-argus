package com.cop.argus.car.service;

import java.util.List;

import com.cop.argus.car.entity.Battery;
import com.cop.argus.common.entity.NameValuePair;

/**
 * 
 * @author chris.liu
 * 
 */
public interface DiagnoseService {

	/**
	 * 返回用户车辆体检信息
	 * 
	 * @param uid
	 * @param errCodes
	 * @return
	 */
	List<NameValuePair> diagnose(int uid, List<String> errCodes);

	/**
	 * 返回用户车辆电瓶电压数据
	 * 
	 * @param uid
	 * @return
	 */
	List<Battery> battery(int uid);
}
