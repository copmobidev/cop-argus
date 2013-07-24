package com.cop.argus.demo.service;

import com.cop.argus.demo.entity.User;

/**
 * 
 * @author chris.liu
 * 
 */
public interface DemoService {

	User retrivePerson(int id) throws Exception;

	void savePerson(User person) throws Exception;
}
