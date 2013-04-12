package com.cop.mobile.test;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author chris.liu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:config/spring/appcontext-*.xml",
		"classpath:config/spring/appcontext-*.xml" })
public class BaseTest {
	static {
		DOMConfigurator.configure("target/classes/log/log4j-argus-mobi.xml");
	}
}