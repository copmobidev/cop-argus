package com.cop.mobi.mycar.service.dao;

import java.util.List;

import com.cop.mobi.common.KeyValuePair;

/**
 * 
 * @author chris.liu
 *
 */
public interface DiagnoseDao {
	
	/**
	 * 获取所有诊断码
	 * @return
	 */
	List<KeyValuePair> getAllDiagnoseItems();
}
