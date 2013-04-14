package com.cop.mobile.test.account;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.account.entity.User;
import com.cop.mobi.account.entity.UserPo;
import com.cop.mobi.account.service.dao.AccountDao;
import com.cop.mobile.test.BaseTest;

/**
 * 
 * @author chris.liu
 * 
 */
public class AccountDaoTest extends BaseTest {

	@Autowired
	private AccountDao accountDao;

	@Test
	public void getUserByIdTest() {
		UserPo userDo = accountDao.getUserById(1);
		if (userDo != null) {
			System.out.println(userDo.getEmail());
		}
	}
	
	@Test
	public void getUserByNameTest() {
		UserPo userDo = accountDao.getUserByName("chris");
		if (userDo != null) {
			System.out.println(userDo.getEmail());
		}
	}
	
	@Test
	public void getUserByEmailTest() {
		UserPo userDo = accountDao.getUserByEmail("chrisliu@gmail.com");
		if (userDo != null) {
			System.out.println(userDo.getName());
		}
	}

	@Test
	public void getUsersTest() {
		try {
			List<Integer> uids = new ArrayList<Integer>();
			uids.add(1);
			uids.add(2);
			List<UserPo> userDos = accountDao.getUsers(StringUtils.join(uids, ","));
			if (userDos != null) {
				for (UserPo userDo : userDos) {
					System.out.println(userDo.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void daoTest4AddUser() {
		User user = new User();
		user.setEmail("nanawu@gmail.com");
		user.setName("nana");
		user.setPwd("111");
		user.setSex(1);
		Object result = accountDao.addUser(user);
		if (result != null) {
			System.out.println(result.toString());
		}
	}
}
