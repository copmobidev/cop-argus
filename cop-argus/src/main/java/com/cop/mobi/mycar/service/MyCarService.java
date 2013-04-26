package com.cop.mobi.mycar.service;

import java.util.List;

import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.DriveRoutePo;
import com.cop.mobi.mycar.entity.MyCar;
import com.cop.mobi.mycar.entity.Span;





/**
 * 
 * @author chris.liu
 * 
 */
public interface MyCarService {

	/**
	 * 根据车辆id获取车辆信息
	 * 
	 * @param obd
	 * @return
	 */
	Result getMyCarById(int id);

	/**
	 * 根据OBD设备id获取车辆信息
	 * 
	 * @param obd
	 * @return
	 */
	Result getMyCarByOBD(String obd);

	/**
	 * 获取用户所有车辆
	 * 
	 * @param uid
	 * @return
	 */
	Result getMyCars(int uid);

	/**
	 * 注册车辆信息
	 * 
	 * @param myCar
	 * @return
	 */
	Result addMyCar(MyCar myCar);

	/**
	 * 删除车辆信息，并非真正意义上的删除，數據依然保留
	 * 
	 * @param myCar
	 * @return
	 */
	Result deleteMyCar(int mcid);

	/**
	 * 获取车辆行车记录
	 * 
	 * @param mcid
	 * @param beginTime 起始时间
	 * @param endTime 结束时间
	 * @param span 时间跨度
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
