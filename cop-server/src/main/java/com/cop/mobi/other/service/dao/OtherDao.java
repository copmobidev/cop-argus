package com.cop.mobi.other.service.dao;

import java.util.List;

import com.cop.mobi.other.entity.ConfigItem;
import com.cop.mobi.other.entity.Feedback;

/**
 * 
 * @author chris.liu
 * 
 */
public interface OtherDao {

	List<ConfigItem> getConfig();

	int addFeedback(Feedback feedback);
}
