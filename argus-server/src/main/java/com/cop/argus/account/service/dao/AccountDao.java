package com.cop.argus.account.service.dao;

import org.apache.ibatis.annotations.Param;

import com.cop.argus.account.service.entity.UserPo;

/**
 * 
 * @author chris.liu
 * 
 */
public interface AccountDao {

	/**
	 * 根据ID获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	UserPo getUserById(@Param(value = "id") int id);

	/**
	 * 根据OBD获取用户信息
	 * 
	 * @param obd
	 * @return
	 */
	UserPo getUserByObd(@Param(value = "obd") String obd);

	/**
	 * 根据邮箱获取用户信息
	 * 
	 * @param email
	 * @return
	 */
	UserPo getUserByEmail(@Param(value = "email") String email);

	/**
	 * 更新用户信息
	 * 
	 * @param id
	 * @param values
	 * @return
	 */
	int updateUserInfo(@Param(value = "id") int id,
			@Param(value = "values") String values);

}
