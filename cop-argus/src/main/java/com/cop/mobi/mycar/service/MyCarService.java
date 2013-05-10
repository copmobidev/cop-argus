package com.cop.mobi.mycar.service;

import java.util.List;
import java.util.Map;

import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.CarBrand;
import com.cop.mobi.mycar.entity.DriveRoutePo;
import com.cop.mobi.mycar.entity.MyCar;
import com.cop.mobi.mycar.entity.MyCarPo;
import com.cop.mobi.mycar.entity.Span;

/**
 * 
 * @author chris.liu
 * 
 */
public interface MyCarService {

	/**
	 * 获取根据关键字索引的汽车品牌
	 * 
	 * @return
	 */
	public Map<String, CarBrand> getCarBrandMap();

	/**
	 * 获取根据id索引的汽车品牌
	 * 
	 * @return
	 */
	public Map<Integer, CarBrand> getRevCarBrandMap();

	/**
	 * 根据车辆id获取车辆信息
	 * 
	 * @param obd
	 * @return
	 */
	MyCar getMyCarById(int id);

	/**
	 * 根据OBD设备id获取车辆信息
	 * 
	 * @param obd
	 * @return
	 */
	MyCar getMyCarBySid(String sid);

	/**
	 * 获取用户所有车辆
	 * 
	 * @param uid
	 * @return
	 */
	List<MyCar> getMyCars(int uid);

	/**
	 * 注册车辆信息
	 * 
	 * @param myCar
	 * @return
	 */
	MyCar addMyCar(int uid, String sid, CarBrand carBrand);

	/**
	 * 更新车辆信息
	 * 
	 * @param myCarPo
	 * @return
	 */
	MyCar updateMyCarInfo(MyCarPo myCarPo);

	/**
	 * 根据车辆id删除车辆
	 * 
	 * @param mcid
	 * @return
	 */
	int deleteMyCar(int mcid);

	/**
	 * 获取车辆行车记录
	 * 
	 * @param mcid
	 * @param beginTime
	 *            起始时间
	 * @param endTime
	 *            结束时间
	 * @param span
	 *            时间跨度
	 * @return
	 */
	Result getDriveRoutes(int mcid, long beginTime, long endTime, Span span);

	/**
	 * 存储客户端行车信息到server并记录数据log
	 * 
	 * @param mcid
	 * @param data
	 * @param startTime
	 * @param endTime
	 * @return 最近一次行车记录
	 */
	Result uploadDriveRoutes(List<DriveRoutePo> driveRoutes);
}
