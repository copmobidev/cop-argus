package com.cop.argus.demo.service.dao;

import org.apache.ibatis.annotations.Param;

import com.cop.argus.demo.service.entity.UserPo;

/**
 * 
 * @author chris.liu
 * 
 */
public interface DemoDao {

	UserPo getUserById(@Param(value = "id") int id);

	int addUser(@Param(value = "name") String name,
			@Param(value = "age") int age,
			@Param(value = "address") String address);
}
