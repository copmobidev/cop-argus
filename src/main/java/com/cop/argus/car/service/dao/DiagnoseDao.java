package com.cop.argus.car.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cop.argus.car.entity.Battery;
import com.cop.argus.common.entity.NameValuePair;

/**
 * 
 * @author chris.liu
 * 
 */
public interface DiagnoseDao {

	List<NameValuePair> getAllErrCode();

	List<Battery> getBattery(@Param(value = "uid") int uid);
}
