package com.cop.mobile.test.poi;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.common.Result;
import com.cop.mobi.poi.service.POIService;
import com.cop.mobile.test.BaseTest;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public class POIServiceTest extends BaseTest {

	@Autowired
	private POIService poiService;

	@Test
	public void fuelStationTest() {
		try {
			Result result = poiService.localFuelStation(31.746816, 120.947143,
					500);

			if (result != null) {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void geoAreaTest() {
		try {
			Result result = poiService.geoAreaList(1);
			if (result != null) {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
