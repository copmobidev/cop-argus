package com.cop.argus.other.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.argus.other.service.entity.ConfigPo;

/**
 * 
 * @author chris.liu
 * 
 */
public interface OtherDao {

	List<ConfigPo> getAllConfig();

	int addFeedback(@Param(value = "ua") String ua,
			@Param(value = "uid") int uid,
			@Param(value = "content") String content,
			@Param(value = "addtime") long addtime);
}
