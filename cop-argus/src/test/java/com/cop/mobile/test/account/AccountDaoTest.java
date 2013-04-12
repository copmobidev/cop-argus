package com.cop.mobile.test.account;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.account.entity.User;
import com.cop.mobi.account.entity.UserPo;
import com.cop.mobi.account.service.AccountService;
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

	@Autowired
	private AccountService accountService;

	@Test
	public void accountDaoTest4User() {
		UserPo userDo = accountDao.getUser("name", "chris");
		if (userDo != null) {
			System.out.println(userDo.getEmail());
		}
	}

	@Test
	public void accountDaoTest4Users() {
		List<UserPo> userDos = accountDao.getUsers("id", "1,2");
		if (userDos != null) {
			System.out.println(userDos.size());
			for (UserPo userDo : userDos) {
				System.out.println(userDo.getEmail());
			}
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
