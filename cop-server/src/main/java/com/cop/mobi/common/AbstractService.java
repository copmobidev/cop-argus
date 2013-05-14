package com.cop.mobi.common;

import org.apache.log4j.Logger;

/**
 * 
 * @author chris.liu
 * 
 */
public abstract class AbstractService {

	protected static final Logger log = Logger.getLogger(AbstractService.class);

	protected static final Message SERVER_INNER_ERROR_MSG = new Message("系统错误",
			"服务器内部错误");
}
