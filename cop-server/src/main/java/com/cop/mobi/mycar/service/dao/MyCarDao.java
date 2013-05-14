package com.cop.mobi.mycar.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.mobi.mycar.entity.CarBrand;
import com.cop.mobi.mycar.entity.DriveRoutePo;
import com.cop.mobi.mycar.entity.MyCarPo;

/**
 * 
 * @author chris.liu
 * 
 */
public interface MyCarDao {

	List<CarBrand> getAllCarBrands();

	MyCarPo getMyCarById(@Param(value = "id") int id);

	MyCarPo getMyCarBySid(@Param(value = "sid") String sid);

	List<MyCarPo> getMyCarsByUid(@Param(value = "uid") int uid);

	int addMyCar(@Param(value = "uid") int uid,
			@Param(value = "sid") String sid, @Param(value = "bid") int bid);

	int updateMyCarInfo(@Param(value = "id") int id,
			@Param(value = "bid") int bid);

	int deleteMyCar(@Param(value = "mcid") int mcid);

	int freezeMyCar(int mcid);

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
