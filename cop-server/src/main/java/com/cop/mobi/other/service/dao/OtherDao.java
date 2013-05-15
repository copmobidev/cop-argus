package com.cop.mobi.other.service.dao;

import java.util.List;

import com.cop.mobi.common.NameValuePair;
import com.cop.mobi.other.entity.Feedback;

/**
 * 
 * @author chris.liu
 * 
 */
public interface OtherDao {

	List<NameValuePair> getConfig();

	int addFeedback(Feedback feedback);
}
