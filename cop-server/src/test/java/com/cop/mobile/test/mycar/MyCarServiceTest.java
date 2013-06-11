package com.cop.mobile.test.mycar;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.DateSpan;
import com.cop.mobi.mycar.entity.DateSpan.Span;
import com.cop.mobi.mycar.entity.MyCarPo;
import com.cop.mobi.mycar.service.DiagnoseService;
import com.cop.mobi.mycar.service.MyCarService;
import com.cop.mobi.mycar.service.dao.MyCarDao;
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;
import com.cop.mobile.test.BaseTest;

/**
 * 
 * @author chris.liu
 * 
 */
public class MyCarServiceTest extends BaseTest {

	@Autowired
	private MyCarService myCarService;

	@Autowired
	private MyCarDao myCarDao;

	@Autowired
	private DiagnoseService diagnoseService;

	@Test
	public void addMyCarTest() {
		try {
			String sid = "E20A39F4-73F5-4BC4-A12F-17D1AD20";
			myCarDao.addMyCar(1, sid, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateMyCarInfoTest() {
		String token = "3cb421fabe8579030b95e85011d863a33aba95b660f9446e9909982160c5db4a";
		Token tk = TokenUtil.parseToken(token);
		MyCarPo myCarPo = new MyCarPo();
		Integer id = new Integer(1);
		myCarPo.setId(id);
		myCarPo.setBid(1);
		Result result = myCarService.updateMyCarInfo(tk, myCarPo);
		if (result != null) {
			try {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("no result return");
		}
	}

	@Test
	public void getDriveRouteTest() {
		try {
			String token = "3cb421fabe8579030b95e85011d863a33aba95b660f9446e9909982160c5db4a";
			Token tk = TokenUtil.parseToken(token);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginDate = sdf.parse("2013-03-02 00:00:00");
			Date endDate = sdf.parse("2013-03-06 00:00:00");
			DateSpan ds = new DateSpan(Span.MONTH, beginDate.getTime(),
					endDate.getTime());
			Result result = myCarService.getDriveRoutes(tk, ds);
			if (result != null) {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void diagnoseTest() {
		String tk = "3cb421fabe8579030b95e85011d863a33aba95b660f9446e9909982160c5db4a";
		String codes = "P1442|P1640|P1698|U1451";
		String[] tmp = codes.split("\\|");
		Token token = TokenUtil.parseToken(tk);

		Result result = diagnoseService.diagnose(token, tmp);
		try {
			JSONObject jo = new JSONObject(result.toString());
			System.out.println(jo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 单组行程数据上传
	 */
	@Test
	public void singleDriveDataUploadTest() {
		try {
			// String data = "";
			// int pieceNum = data.length() / 40 - 1;
			// String summary = data.substring(pieceNum * 40, data.length());
			// DriveSummary ds = DriveDataParser.parseDrivingSummary(summary);
			// myCarDao.uploadDrivingData(1, ds, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 多组行程数据上传
	 */
	@Test
	public void multiDriveDataUploadTest() {

	}
}
