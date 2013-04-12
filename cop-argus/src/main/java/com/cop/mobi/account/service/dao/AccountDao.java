package com.cop.mobi.account.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.mobi.account.entity.User;
import com.cop.mobi.account.entity.UserPo;

/**
 * 
 * @author chris.liu
 * 
 */
public interface AccountDao {

	UserPo getUser(@Param(value = "key") String key,
			@Param(value = "val") String val);

	List<UserPo> getUsers(@Param(value = "key") String key,
			@Param(value = "val") String val);

	Object addUser(User user);

}
