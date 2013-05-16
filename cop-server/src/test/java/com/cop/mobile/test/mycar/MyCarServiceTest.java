package com.cop.mobile.test.mycar;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.DateSpan;
import com.cop.mobi.mycar.entity.DateSpan.Span;
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
	public void myCarServiceTest4Route() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginDate = sdf.parse("2013-03-02 00:00:00");
			Date endDate = sdf.parse("2013-03-06 00:00:00");
			DateSpan ds = new DateSpan(Span.MONTH, beginDate.getTime(),
					endDate.getTime());
			Result result = myCarService.getDriveRoutes(1, ds);
			if (result != null) {
				JSONObject jo = new JSONObject(result.toString());
				System.out.println(jo.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void diagnoseServiceTest4diagnose() {
		String tk = "";
		String[] codes = { "P1442", "P1640", "P1698", "U1451" };
		Token token = TokenUtil.parseToken(tk);

		Result result = diagnoseService.diagnose(token, codes);
		try {
			JSONObject jo = new JSONObject(result.toString());
			System.out.println(jo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		date = sdf.parse("2013-03-06 08:21:11");
		System.out.println("2013-03-06 08:21:11:\t" + date.getTime());

		date = sdf.parse("2013-03-06 08:22:11");
		System.out.println("2013-03-06 08:22:11:\t" + date.getTime());

		date = sdf.parse("2013-03-06 08:23:11");
		System.out.println("2013-03-06 08:23:11:\t" + date.getTime());

		date = sdf.parse("2013-03-06 08:24:11");
		System.out.println("2013-03-06 08:24:11:\t" + date.getTime());

		date = sdf.parse("2013-03-06 08:25:11");
		System.out.println("2013-03-06 08:25:11:\t" + date.getTime());

		date = sdf.parse("2013-03-06 08:26:11");
		System.out.println("2013-03-06 08:26:11:\t" + date.getTime());

		date = sdf.parse("2013-03-06 08:27:11");
		System.out.println("2013-03-06 08:27:11:\t" + date.getTime());

		date = sdf.parse("2013-03-06 08:28:11");
		System.out.println("2013-03-06 08:28:11:\t" + date.getTime());

		date = sdf.parse("2013-03-06 08:29:11");
		System.out.println("2013-03-06 08:29:11:\t" + date.getTime());

		date = sdf.parse("2013-03-06 08:30:11");
		System.out.println("2013-03-06 08:30:11:\t" + date.getTime());

		date = sdf.parse("2013-05-15 00:00:00");
		System.out.println("2013-02-15 00:00:00:\t" + date.getTime());

		String test = "%7B%22status%22%3A400%2C%22data%22%3A%7B%22title%22%3A%22login+error%22%2C%22content%22%3A%22%E7%94%A8%E6%88%B7%E4%B8%8D%E5%AD%98%E5%9C%A8%22%7D%7D";
		System.out.println(URLDecoder.decode(test, "utf-8"));
	}
}
