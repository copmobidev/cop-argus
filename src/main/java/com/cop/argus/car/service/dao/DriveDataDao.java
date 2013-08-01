package com.cop.argus.car.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.argus.car.entity.TripData;

/**
 * 
 * @author chris.liu
 * 
 */
public interface DriveDataDao {
	/**
	 * 获取行程数据
	 * 
	 * @param uid
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<TripData> getTripData(@Param(value = "uid") int uid,
			@Param(value = "beginTime") long beginTime,
			@Param(value = "endTime") long endTime);

	/**
	 * 插入行程数据
	 * 
	 * @param values
	 * @return
	 */
	int addDriveData(@Param(value = "values") String values);
}
