package com.cop.mobi.rest.core;

import org.apache.log4j.Logger;

import com.cop.mobi.common.Message;

/**
 * 
 * @author chris.liu
 * 
 */
public abstract class AbstractAction {
	protected static final Logger log = Logger.getLogger("root.log");

	protected static final Message PARAM_ERROR_MSG = new Message("系统错误",
			"请求参数错误");
	
	protected static final Message SERVER_INNER_ERROR_MSG = new Message("系统错误",
			"服务器内部错误");

}
