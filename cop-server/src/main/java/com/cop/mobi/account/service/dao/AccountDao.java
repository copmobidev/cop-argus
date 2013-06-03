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

	UserPo getUserById(@Param(value = "id") int id);

	UserPo getUserByOBD(@Param(value = "obd") String obd);

	UserPo getUserByName(@Param(value = "name") String name);

	UserPo getUserByEmail(@Param(value = "email") String email);

	List<UserPo> getUsers(@Param(value = "ids") String ids);

	int addUser(@Param(value = "obd") String obd,
			@Param(value = "registerTime") long registerTime);

	/**
	 * 更新用户信息
	 * 
	 * @param value
	 *            数据格式为col1=val1,...,coln=valn
	 * @return
	 */
	int updateUserInfo(@Param(value = "id") int id,
			@Param(value = "values") String values);

	int updateUserProfile(@Param(value = "id") int id,
			@Param(value = "profile") String profile);

	int deleteUser(@Param(value = "uid") int uid);

}
