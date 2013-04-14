package com.cop.mobi.mycar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cop.mobi.common.AbstractService;
import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.DriveRoute;
import com.cop.mobi.mycar.entity.DriveRoutePo;
import com.cop.mobi.mycar.entity.MyCar;
import com.cop.mobi.mycar.service.MyCarService;
import com.cop.mobi.mycar.service.dao.MyCarDao;
import com.cop.mobi.rest.core.SpringApplicationContext;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("myCarService")
public class MyCarServiceImpl extends AbstractService implements MyCarService {

	private static final String Tag = "MyCarServiceImpl";

	private static final Logger MyCarLog = Logger.getLogger("mycar-log");

	private static MyCarDao myCarDao;

	static {
		try {
			myCarDao = (MyCarDao) SpringApplicationContext.getBean("myCarDao");
		} catch (Exception e) {
			error(Tag, "init error", e);
		}
	}

	@Override
	public Result getMyCarById(int id) {
		try {
			MyCar myCar = myCarDao.getMyCarById(id);
			if (myCar != null) {
				return new Result(ResultStatus.RS_OK, myCar);
			} else {
				return new Result(ResultStatus.RS_FAIL, new Message("警告",
						"未发现该车辆"));
			}
		} catch (Exception e) {
			error(Tag, String.format("getMyCarByID(%d)", id), e);
			return new Result(ResultStatus.RS_ERROR, new Message("系统错误",
					"服务器内部错误"));
		}
	}

	@Override
	public Result getMyCarByOBD(String obd) {
		try {
			MyCar myCar = myCarDao.getMyCarByOBD(obd);
			if (myCar != null) {
				return new Result(ResultStatus.RS_OK, myCar);
			} else {
				return new Result(ResultStatus.RS_FAIL, new Message("警告",
						"未发现该车辆"));
			}
		} catch (Exception e) {
			error(Tag, String.format("getMyCar(%d)", obd), e);
			return new Result(ResultStatus.RS_ERROR, new Message("系统错误",
					"服务器内部错误"));
		}
	}

	@Override
	public Result getMyCars(int uid) {
		try {
			List<MyCar> myCars = myCarDao.getMyCarsByUid(uid);
			if (myCars != null && myCars.size() > 0) {
				String str = StringUtils.join(myCars, ",");
				return new Result(ResultStatus.RS_OK, str);
			} else {
				return new Result(ResultStatus.RS_FAIL, new Message("警告",
						"未发现该车辆"));
			}
		} catch (Exception e) {
			error(Tag, String.format("getMyCarByUid(%d)", uid), e);
			return new Result(ResultStatus.RS_ERROR, new Message("系统错误",
					"服务器内部错误"));
		}
	}

	@Override
	public Result addMyCar(MyCar myCar) {
		try {
			MyCar confirm = myCarDao.getMyCarByOBD(myCar.getObd());
			if (confirm != null) {
				return new Result(ResultStatus.RS_FAIL, new Message("注册错误",
						"该OBD设备已存在"));
			} else {
				myCarDao.addMyCar(myCar);
			}

		} catch (Exception e) {

		}

		return null;
	}

	@Override
	public Result getDriveRoutes(int mcid, long beginTime, long endTime) {
		try {
			List<DriveRoutePo> drps = myCarDao.getDriveRoutes(mcid, beginTime,
					endTime);
			if (drps != null && drps.size() > 0) {
				List<DriveRoute> drs = new ArrayList<DriveRoute>();
				for (DriveRoutePo drp : drps) {
					DriveRoute dr = new DriveRoute(drp);
					drs.add(dr);
				}
				Result result = new Result(ResultStatus.RS_OK, drs);
				return result;
			} else {
				return new Result(ResultStatus.RS_FAIL, new Message("警告",
						"未发现该车辆行车信息"));
			}
		} catch (Exception e) {
			error(Tag, String.format("getMyCarStatus(%d, %d, %d)", mcid,
					beginTime, endTime), e);
			return new Result(ResultStatus.RS_ERROR, new Message("系统错误",
					"服务器内部错误"));
		}
	}

	@Override
	public Result uploadDriveRoutes(int mcid, String data, long startTime,
			long endTime) {
		MyCarLog.info(String.format("%d-%s", mcid, data));
		return null;
	}

}
