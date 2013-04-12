package com.cop.mobi.rest.core;

import org.apache.log4j.Logger;

import com.cop.mobi.common.AbstractService;

/**
 * 
 * @author chris.liu
 *
 */
public abstract class AbstractAction {
	private static final Logger log = Logger
			.getLogger(AbstractService.class);
	
	
	public static void error(Object tag, Object msg, Throwable e) {
		log.error(String.format("%s->%s", tag, msg), e);
	}
}
