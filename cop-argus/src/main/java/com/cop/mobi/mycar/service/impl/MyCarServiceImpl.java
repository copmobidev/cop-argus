package com.cop.mobi.mycar.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cop.mobi.common.AbstractService;
import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.DriveRoute;
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
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	@Override
	public Result getMyCarById(int id) {
		Result result = null;
		try {
			MyCar myCar = myCarDao.getMyCarById(id);
			if (myCar != null) {
				result = new Result(ResultStatus.RS_OK, myCar);
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("警告",
						"未发现该车辆"));
			}
		} catch (Exception e) {
			log.error(
					String.format("%s:%s", Tag,
							String.format("getMyCarByID(%d)", id)), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result getMyCarByOBD(String obd) {
		Result result = null;
		try {
			MyCar myCar = myCarDao.getMyCarByOBD(obd);
			if (myCar != null) {
				result = new Result(ResultStatus.RS_OK, myCar);
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("警告",
						"未发现该车辆"));
			}
		} catch (Exception e) {
			log.error(
					String.format("%s:%s", Tag,
							String.format("getMyCar(%d)", obd)), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result getMyCars(int uid) {
		Result result = null;
		try {
			List<MyCar> myCars = myCarDao.getMyCarsByUid(uid);
			if (myCars != null && myCars.size() > 0) {
				String str = StringUtils.join(myCars, ",");
				result = new Result(ResultStatus.RS_OK, str);
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("警告",
						"未发现该车辆"));
			}
		} catch (Exception e) {
			log.error(
					String.format("%s:%s", Tag,
							String.format("getMyCarByUid(%d)", uid)), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result addMyCar(MyCar myCar) {
		Result result = null;
		try {
			MyCar existedCar = myCarDao.getMyCarByOBD(myCar.getObd());
			if (existedCar != null) {
				result = new Result(ResultStatus.RS_FAIL, new Message("警告",
						"该OBD设备已存在"));
			} else {
				int optCode = myCarDao.addMyCar(myCar);
				if (optCode == 1) {
					result = new Result(ResultStatus.RS_OK, myCar);
				} else {
					result = new Result(ResultStatus.RS_FAIL, new Message("警告",
							"添加该车辆信息失败"));
				}
			}

		} catch (Exception e) {
			log.error(
					String.format("%s:%s:%s", Tag, "addMyCar() error", myCar),
					e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}

		return result;
	}

	@Override
	public Result deleteMyCar(int mcid) {
		Result result = null;
		try {
			int optCode = myCarDao.freezeMyCar(mcid);
			if (optCode == 1) {
				result = new Result(ResultStatus.RS_OK, new Message("提示",
						"成功删除该车辆信息"));
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("警告",
						"删除该车辆信息失败"));
			}
		} catch (Exception e) {
			log.error(
					String.format("%s:%s:%d", Tag, "deleteMyCar() error", mcid),
					e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result getDriveRoutes(int mcid, long beginTime, long endTime) {
		Result result = null;
		try {
			List<DriveRoute> drs = myCarDao.getDriveRoutes(mcid, beginTime,
					endTime);
			if (drs != null && drs.size() > 0) {
				result = new Result(ResultStatus.RS_OK, drs);
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("警告",
						"未发现该车辆行车信息"));
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, String.format(
					"getMyCarStatus(%d, %d, %d)", mcid, beginTime, endTime)), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result getOilCost(int mcid, long beginTime, long endTime) {

		return null;
	}

	@Override
	public Result getSpeed(int mcid, long beginTime, long endTime) {

		return null;
	}

	@Override
	public Result getTemperature(int mcid, long beginTime, long endTime) {

		return null;
	}

	@Override
	public Result uploadDriveRoutes(List<DriveRoute> driveRoutes) {
		for (DriveRoute dr : driveRoutes) {
			MyCarLog.info(String.format("%d-%s", dr.getMcid(), dr.getRoute()));
		}

		Result result = null;
		try {

		} catch (Exception e) {
			log.error(String.format("%s:%s:%s", Tag, "uploadDriveRoutes()"), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

}
