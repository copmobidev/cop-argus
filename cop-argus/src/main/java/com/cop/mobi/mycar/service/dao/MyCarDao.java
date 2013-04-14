package com.cop.mobi.mycar.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.mobi.mycar.entity.DriveRoutePo;
import com.cop.mobi.mycar.entity.MyCar;

/**
 * 
 * @author chris.liu
 * 
 */
public interface MyCarDao {

	MyCar getMyCarById(@Param(value = "id") int id);

	MyCar getMyCarByOBD(@Param(value = "obd") String obd);

	List<MyCar> getMyCarsByUid(@Param(value = "uid") int uid);

	int addMyCar(MyCar myCar);

	/**
	 * get my car status by my car id during beginDate and end date
	 * 
	 * @param mcid
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	List<DriveRoutePo> getDriveRoutes(@Param(value = "mcid") int mcid,
			@Param(value = "beginTime") long beginTime,
			@Param(value = "endTime") long endTime);
}
