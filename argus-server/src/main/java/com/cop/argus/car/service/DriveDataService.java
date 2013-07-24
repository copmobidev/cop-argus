package com.cop.argus.car.service;

import java.util.List;

import com.cop.argus.car.entity.DriveDataServiceException;
import com.cop.argus.common.entity.Message;

/**
 * 
 * @author chris.liu
 * 
 */
public interface DriveDataService {

	/**
	 * 上传行程数据
	 * 
	 * @param uid
	 * @param datas
	 */
	Message uploadDriveData(int uid, List<String> datas)
			throws DriveDataServiceException;

}
