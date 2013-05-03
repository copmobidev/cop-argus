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
import com.cop.mobi.mycar.entity.MyCarPo;
import com.cop.mobi.mycar.entity.Span;
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
	public Result getMyCarByVIN(String vin) {
		Result result = null;
		try {
			MyCar myCar = myCarDao.getMyCarByVIN(vin);
			if (myCar != null) {
				result = new Result(ResultStatus.RS_OK, myCar);
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("警告",
						"未发现该车辆"));
			}
		} catch (Exception e) {
			log.error(
					String.format("%s:%s", Tag,
							String.format("getMyCar(%d)", vin)), e);
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
	public Result addMyCar(MyCarPo myCarPo) {
		Result result = null;
		try {
			MyCar existedCar = myCarDao.getMyCarByVIN(myCarPo.getVin());
			if (existedCar != null) {
				result = new Result(ResultStatus.RS_FAIL, new Message("警告",
						"该OBD设备已存在"));
			} else {
				int optCode = myCarDao.addMyCar(myCarPo);
				if (optCode == 1) {
					result = new Result(ResultStatus.RS_OK, myCarPo);
				} else {
					result = new Result(ResultStatus.RS_FAIL, new Message("警告",
							"添加该车辆信息失败"));
				}
			}

		} catch (Exception e) {
			log.error(
					String.format("%s:%s:%s", Tag, "addMyCar() error", myCarPo),
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
	public Result getDriveRoutes(int mcid, long beginTime, long endTime,
			Span span) {
		Result result = null;
		try {
			List<DriveRoutePo> drps = myCarDao.getDriveRoutes(mcid, beginTime,
					endTime);
			List<DriveRoute> drs = new ArrayList<DriveRoute>();
			for (DriveRoutePo drp : drps) {
				drs.add(new DriveRoute(drp));
			}
			if (drps != null && drps.size() > 0) {
				String actions = parseAction(drs, span);
				String oilcosts = parseOilCost(drs, span);
				String speeds = parseSpeed(drs, span);
				String temps = parseTemperature(drs, span);
				String data = String
						.format("\"action\":\"%s\",\"oilcost\":\"%s\",\"speed\":\"%s\",\"temp\":\"%s\"",
								actions, oilcosts, speeds, temps);

				result = new Result(ResultStatus.RS_OK, data);
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

	/**
	 * 获取给定时间区域内的各项指标数据（加速、刹车等）统计
	 * 
	 * @param routes
	 * @param span
	 * @return 
	 *         break-span0:value0|...|spanN:valueN;acc-span0:value0|...|spanN:valueN
	 */
	private String parseAction(List<DriveRoute> routes, Span span) {
		for (DriveRoute route : routes) {
			switch (span) {
			case PIECE:
				
				break;
			case WEEK:
				break;
			case MONTH:
				break;
			case YEAR:
				break;
			}
		}
		return null;
	}

	/**
	 * 获取给定时间区域内的平均油耗
	 * 
	 * @param routes
	 * @param span
	 * @return the string with format span0:value0|span1:value1|...|spanN:valueN
	 */
	private String parseOilCost(List<DriveRoute> routes, Span span) {

		return null;
	}

	/**
	 * 获取给定时间区域内的平均速度
	 * 
	 * @param routes
	 * @param span
	 * @return the string with format span0:value0|span1:value1|...|spanN:valueN
	 */
	private String parseSpeed(List<DriveRoute> routes, Span span) {

		return null;
	}

	/**
	 * 获取给定时间区域内的平均温度
	 * 
	 * @param routes
	 * @param span
	 * @return the string with format span0:value0|span1:value1|...|spanN:valueN
	 */
	private String parseTemperature(List<DriveRoute> routes, Span span) {

		return null;
	}

	@Override
	public Result uploadDriveRoutes(List<DriveRoutePo> driveRoutes) {
		for (DriveRoutePo dr : driveRoutes) {
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
