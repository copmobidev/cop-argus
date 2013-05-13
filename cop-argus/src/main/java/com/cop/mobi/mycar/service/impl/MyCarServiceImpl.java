package com.cop.mobi.mycar.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cop.mobi.common.AbstractService;
import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.CarBrand;
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

	private static Map<String, CarBrand> CarBrandMap = new HashMap<String, CarBrand>();
	private static Map<Integer, CarBrand> RevCarBrandMap = new HashMap<Integer, CarBrand>();

	static {
		init();
	}

	public static void init() {
		try {
			myCarDao = (MyCarDao) SpringApplicationContext.getBean("myCarDao");
			List<CarBrand> carBrands = myCarDao.getAllCarBrands();
			if (carBrands != null && carBrands.size() > 0) {
				for (CarBrand carBrand : carBrands) {
					CarBrandMap.put(String.format("%s%s%s",
							carBrand.getBrand(), carBrand.getModel(),
							carBrand.getEngine()), carBrand);

					RevCarBrandMap.put(carBrand.getId(), carBrand);
				}
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	@Override
	public Map<String, CarBrand> getCarBrandMap() {
		return CarBrandMap;
	}

	@Override
	public Map<Integer, CarBrand> getRevCarBrandMap() {
		return RevCarBrandMap;
	}

	@Override
	public MyCar getMyCarById(int id) {
		MyCar myCar = null;
		try {
			MyCarPo myCarPo = myCarDao.getMyCarById(id);
			if (myCarPo != null) {
				CarBrand carBrand = CarBrandMap.get(myCarPo.getBid());
				myCar = new MyCar(myCarPo.getId(), myCarPo.getUid(),
						myCarPo.getSid(), carBrand);
			}
		} catch (Exception e) {
			log.error(
					String.format("%s:%s", Tag,
							String.format("getMyCarByID(%d)", id)), e);
			myCar = null;
		}
		return myCar;
	}

	@Override
	public MyCar getMyCarBySid(String sid) {
		MyCar myCar = null;
		try {
			MyCarPo myCarPo = myCarDao.getMyCarBySid(sid);
			if (myCarPo != null) {
				CarBrand carBrand = RevCarBrandMap.get(myCarPo.getBid());
				if (carBrand != null) {
					myCar = new MyCar(myCarPo.getId(), myCarPo.getUid(),
							myCarPo.getSid(), carBrand);
					return myCar;
				}
			}
		} catch (Exception e) {
			log.error(
					String.format("%s:%s", Tag,
							String.format("getMyCar(%d)", sid)), e);
			myCar = null;
		}
		return myCar;
	}

	@Override
	public List<MyCar> getMyCars(int uid) {
		List<MyCar> myCars = null;
		try {
			List<MyCarPo> myCarPos = myCarDao.getMyCarsByUid(uid);
			if (myCarPos != null && myCarPos.size() > 0) {
				myCars = new ArrayList<MyCar>();
				for (MyCarPo myCarPo : myCarPos) {
					CarBrand carBrand = RevCarBrandMap.get(myCarPo.getBid());
					MyCar myCar = new MyCar(myCarPo.getId(), myCarPo.getUid(),
							myCarPo.getSid(), carBrand);
					myCars.add(myCar);
				}
			}
		} catch (Exception e) {
			log.error(
					String.format("%s:%s", Tag,
							String.format("getMyCarByUid(%d)", uid)), e);
			myCars = null;
		}
		return myCars;
	}

	@Override
	public MyCar addMyCar(int uid, String sid, CarBrand carBrand) {
		MyCar myCar = null;
		try {
			CarBrand cb = CarBrandMap.get(String.format("%s%s%s",
					carBrand.getBrand(), carBrand.getModel(),
					carBrand.getEngine()));
			if (cb == null) {
				return null;
			}

			MyCarPo existedCarPo = myCarDao.getMyCarBySid(sid);
			if (existedCarPo != null) {
				return null;
			} else {
				int optCode = myCarDao.addMyCar(uid, sid, cb.getId());
				if (optCode == 1) {
					MyCarPo myCarPo = myCarDao.getMyCarBySid(sid);
					if (myCarPo != null) {
						myCar = new MyCar(myCarPo.getId(), myCarPo.getUid(),
								sid, cb);
					}
				}
			}
		} catch (Exception e) {
			log.error(
					String.format("%addMyCar() error with param:%s", Tag, sid),
					e);
			myCar = null;
		}
		return myCar;
	}

	@Override
	public MyCar updateMyCarInfo(MyCarPo myCarPo) {
		MyCar myCar = null;
		try {
			myCarDao.updateMyCarInfo(myCarPo.getId(), myCarPo.getBid());

			MyCarPo updatedMyCarPo = myCarDao.getMyCarById(myCarPo.getId());
			CarBrand cb = RevCarBrandMap.get(myCarPo.getBid());
			if (cb != null) {
				myCar = new MyCar(updatedMyCarPo.getId(),
						updatedMyCarPo.getUid(), updatedMyCarPo.getSid(), cb);
			}
		} catch (Exception e) {
			log.error(String.format("%s:updateMyCarInfo() error with param:%s",
					Tag, myCarPo.getId()), e);
			myCar = null;
		}
		return myCar;
	}

	@Override
	public int deleteMyCar(int mcid) {
		try {
			return myCarDao.deleteMyCar(mcid);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "deleteMyCar() error"), e);
		}
		return 0;
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
//				drs.add(new DriveRoute(drp));
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
