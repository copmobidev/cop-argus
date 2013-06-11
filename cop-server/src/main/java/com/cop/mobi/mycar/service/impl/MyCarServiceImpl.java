package com.cop.mobi.mycar.service.impl;

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
import com.cop.mobi.mycar.entity.DateSpan;
import com.cop.mobi.mycar.entity.DateSpan.Span;
import com.cop.mobi.mycar.entity.DriveRoutePo;
import com.cop.mobi.mycar.entity.MyCar;
import com.cop.mobi.mycar.entity.MyCarPo;
import com.cop.mobi.mycar.service.MyCarService;
import com.cop.mobi.mycar.service.dao.MyCarDao;
import com.cop.mobi.rest.core.SpringApplicationContext;
import com.cop.mobi.rest.core.Token;
import com.cop.mobi.rest.core.TokenUtil;

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
			log.error(String.format("%s:getMyCarByID(%s)", Tag, id), e);
		}
		return myCar;
	}

	@Override
	public MyCar getMyCarByUid(int uid) {
		try {
			List<MyCarPo> myCarPos = myCarDao.getMyCarsByUid(uid);
			for (MyCarPo myCarPo : myCarPos) {
				if (myCarPo.getIsBound() == 1) {
					CarBrand carBrand = RevCarBrandMap.get(myCarPo.getBid());
					if (carBrand != null) {
						MyCar myCar = new MyCar(myCarPo.getId(),
								myCarPo.getUid(), myCarPo.getSid(), carBrand);
						return myCar;
					}
				}
			}
		} catch (Exception e) {
			log.error(String.format("%s:getMyCarByUid(%s) error", Tag, uid), e);
		}
		return null;
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
	public Result updateMyCarInfo(Token token, MyCarPo myCarPo) {
		Result result = null;
		try {
			MyCar myCar = null;
			myCarDao.updateMyCarInfo(myCarPo.getId(), myCarPo.getBid());
			MyCarPo updatedMyCarPo = myCarDao.getMyCarById(myCarPo.getId());
			CarBrand cb = RevCarBrandMap.get(myCarPo.getBid());
			if (cb != null && updatedMyCarPo != null) {
				myCar = new MyCar(updatedMyCarPo.getId(),
						updatedMyCarPo.getUid(), updatedMyCarPo.getSid(), cb);
				String tk = TokenUtil.generateToken(token.getUid(),
						token.getMcid(), 1);
				String tmp = String.format(
						"{\"token\":\"%s\",\"mycar\":\"%s\"}", tk,
						myCar.toLCString());
				result = new Result(ResultStatus.RS_OK, tmp);
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("警告",
						"车辆信息更新失败"));
			}
		} catch (Exception e) {
			log.error(String.format("%s:updateMyCarInfo() error with param:%s",
					Tag, myCarPo.getId()), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public int deleteMyCar(int id) {
		try {
			return myCarDao.deleteMyCar(id);
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "deleteMyCar() error"), e);
		}
		return -1;
	}

	@Override
	public Result getDriveRoutes(Token token, DateSpan dataSpan) {
		Result result = null;
		String data = parseRoute(token.getMcid(), dataSpan);
		if (data != null) {
			result = new Result(ResultStatus.RS_OK, data);
		} else {
			result = new Result(ResultStatus.RS_FAIL, new Message("警告",
					"未发现该车辆行车信息"));
		}
		return result;
	}

	@Override
	public Result uploadDriveRoutes(Token token, String[] orginDatas) {
		for (String data : orginDatas) {
			MyCarLog.info(String.format("%d-%s", token.getMcid(), data));
		}

		Result result = null;
		try {
			for (int i = 0; i < orginDatas.length; ++i) {
				int pieceNum = orginDatas[i].length() / 80 - 1;
				// DriveSummary driveSummary = DriveDataParser
				// .parseDrivingSummary(orginDatas[i].substring(
				// pieceNum * 80, orginDatas[i].length()));
				// myCarDao.uploadDrivingData(mcid, driveSummary,
				// orginDatas[i]);
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s:%s", Tag, "uploadDriveRoutes()"), e);
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
	private String parseRoute(int mcid, DateSpan dataSpan) {
		try {
			List<DriveRoutePo> routePos = myCarDao.getDriveRoutes(mcid,
					dataSpan.getBeginTime(), dataSpan.getEndTime());
			if (dataSpan.getSpan() == Span.PIECE) {
				// 获取单次行程数据

			} else {
				// 获取行程summary数据
				switch (dataSpan.getSpan()) {
				case WEEK:
					break;
				case MONTH:
					break;
				case YEAR:
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag,
					String.format("getMyCarStatus(%d, %d)", mcid, dataSpan)), e);
		}
		return null;
	}
}
