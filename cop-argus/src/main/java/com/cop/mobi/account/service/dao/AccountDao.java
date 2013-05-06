package com.cop.mobi.account.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.mobi.account.entity.UserPo;

/**
 * 
 * @author chris.liu
 * 
 */
public interface AccountDao {

	UserPo getUserById(@Param(value = "uid") int uid);

	UserPo getUserByOBD(@Param(value = "obd") String obd);

	UserPo getUserByName(@Param(value = "name") String name);

	UserPo getUserByEmail(@Param(value = "email") String email);

	List<UserPo> getUsers(@Param(value = "uids") String uids);

	int addUser(UserPo userPo);

	int deleteUser(@Param(value = "uid") int uid);

	int updateUserProfile(@Param(value = "uid") int uid,
			@Param(value = "profile") String profile);

}
