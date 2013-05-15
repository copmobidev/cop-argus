package com.cop.mobi.mycar.service;

import com.cop.mobi.common.Result;
import com.cop.mobi.rest.core.Token;

/**
 * 
 * @author chris.liu
 * 
 */
public interface DiagnoseService {

	/**
	 * 获取指定诊断码信息
	 * 
	 * @param codes
	 * @return
	 */
	Result diagnose(Token token, String[] codes);
}
