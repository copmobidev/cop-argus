package com.cop.argus.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cop.argus.demo.entity.User;
import com.cop.argus.demo.service.DemoService;
import com.cop.argus.demo.service.dao.DemoDao;
import com.cop.argus.demo.service.entity.UserPo;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("dummyService")
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDao demoDao;

	@Override
	public User retrivePerson(int id) {
		User user = null;
		try {
			UserPo userPo = demoDao.getUserById(id);
			if (userPo != null) {
				user = new User();
				user.setId(id);
				user.setName(userPo.getName());
				user.setAge(userPo.getAge());
				user.setAddress(userPo.getAddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void savePerson(User person) {
		System.out.println("\n\nsaving" + person);
	}

}
