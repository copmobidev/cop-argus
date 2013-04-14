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

	UserPo getUserById(@Param(value = "uid") int uid);

	UserPo getUserByName(@Param(value = "name") String name);

	UserPo getUserByEmail(@Param(value = "email") String email);

	List<UserPo> getUsers(@Param(value = "uids") String uids);

	Object addUser(User user);

}
