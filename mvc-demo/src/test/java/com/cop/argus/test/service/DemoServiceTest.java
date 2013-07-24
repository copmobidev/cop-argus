package com.cop.argus.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.argus.demo.entity.User;
import com.cop.argus.demo.service.DemoService;

/**
 * 
 * @author chris.liu
 * 
 */
public class DemoServiceTest extends BasicTest {

	@Autowired
	private DemoService dummyService;

	@Test
	public void retriveTest() {
		try {
			User user = dummyService.retrivePerson(2);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
