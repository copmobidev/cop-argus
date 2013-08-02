package com.cop.argus.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.argus.car.entity.Battery;
import com.cop.argus.car.service.DiagnoseService;
import com.cop.argus.common.entity.NameValuePair;
import com.cop.argus.common.util.DataFormater;
import com.cop.argus.test.BasicTest;

/**
 * 
 * @author chris.liu
 * 
 */
public class DiagnoseServiceTest extends BasicTest {

	@Autowired
	private DiagnoseService diagnoseService;

	@Test
	public void diagnoseTest() {
		List<String> errCodes = new ArrayList<String>();
		errCodes.add("B0103");
		errCodes.add("B0105");
		errCodes.add("B0106");
		errCodes.add("B0107");
		try {
			List<NameValuePair> result = diagnoseService.diagnose(1, errCodes);
			System.out.println(DataFormater.format(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void batteryTest() {
		try {
			List<Battery> bats = diagnoseService.battery(1);
			System.out.println(DataFormater.format(bats));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
