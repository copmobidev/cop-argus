package com.cop.mobi.common;

import org.apache.log4j.Logger;

/**
 * 
 * @author chris.liu
 *
 */
public abstract class AbstractService {
	private static final Logger log = Logger
			.getLogger(AbstractService.class);
	
	
	public static void error(Object tag, Object msg, Throwable e) {
		log.error(String.format("%s->%s", tag, msg), e);
	}
	
}
