package com.cop.mobile.test.mycar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.common.KeyValuePair;
import com.cop.mobi.mycar.entity.DriveRoutePo;
import com.cop.mobi.mycar.entity.MyCar;
import com.cop.mobi.mycar.service.dao.DiagnoseDao;
import com.cop.mobi.mycar.service.dao.MyCarDao;
import com.cop.mobi.mycar.service.dao.OilBillDao;
import com.cop.mobile.test.BaseTest;

/**
 * 
 * @author chris.liu
 * 
 */
public class MyCarDaoTest extends BaseTest {

	@Autowired
	private MyCarDao myCarDao;

	@Autowired
	private OilBillDao oilBillDao;

	@Autowired
	private DiagnoseDao diagnoseDao;

	@Test
	public void getMyCarTest() {
		try {
			List<MyCar> myCars = myCarDao.getMyCarsByUid(2);
			if (myCars != null && myCars.size() > 0) {
				String str = StringUtils.join(myCars, ",");
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addMyCarTest() {
		MyCar registerCar = new MyCar();
		registerCar.setUid(3);
		registerCar.setNameCH("大众宝来1.6T");
		registerCar.setObd("E20A39F4-73F5-4BC4-A12F-17D1AD07A963");
		registerCar.setPrice(125000.0);
		registerCar.setBuyDate(1360857600000l);
		try {
			int result = myCarDao.addMyCar(registerCar);
			if (result == 1) {
				MyCar finalMyCar = myCarDao.getMyCarByOBD("E20A39F4-73F5-4BC4-A12F-17D1AD07A963");
				if (finalMyCar != null) {
					System.out.println(finalMyCar.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void statusTest() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginTime = sdf.parse("2013-03-02 00:00:00");
			Date endTime = sdf.parse("2013-03-03 00:00:00");
			List<DriveRoutePo> status = myCarDao.getDriveRoutes(1,
					beginTime.getTime(), endTime.getTime());
			if (status != null) {
				for (DriveRoutePo s : status) {
					System.out.println(sdf.format(new Date(s.getAddTime())));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void diagnoseTest() {
		try {
			List<KeyValuePair> diagnoseItems = diagnoseDao
					.getAllDiagnoseItems();
			if (diagnoseItems != null && diagnoseItems.size() > 0) {
				for (KeyValuePair item : diagnoseItems) {
					System.out.println(item.getKey() + "\t"
							+ item.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
