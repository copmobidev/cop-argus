package com.cop.argus.car.service.dao;

import java.util.List;

import com.cop.argus.common.entity.NameValuePair;

/**
 * 
 * @author chris.liu
 * 
 */
public interface DiagnoseDao {

	List<NameValuePair> getAllErrCode();
}
