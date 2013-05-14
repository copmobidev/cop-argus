package com.cop.mobi.other.service.dao;

import com.cop.mobi.other.entity.Config;
import com.cop.mobi.other.entity.Feedback;

/**
 * 
 * @author chris.liu
 * 
 */
public interface OtherDao {

	Config getConfig();

	int addFeedback(Feedback feedback);
}
