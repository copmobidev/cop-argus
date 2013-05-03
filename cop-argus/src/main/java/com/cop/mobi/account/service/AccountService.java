package com.cop.mobi.account.service;

import com.cop.mobi.account.entity.User;
import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.MyCar;
import com.cop.mobi.mycar.entity.MyCarPo;

/**
 * 
 * @author chris.liu
 * 
 */
public interface AccountService {

	/**
	 * 根据用户和车辆注册
	 * 
	 * @param user
	 * @param myCar
	 * @return
	 */
	Result register(User user, MyCarPo myCar);

	/**
	 * 登陆
	 * 
	 * @param user
	 * @return
	 */
	Result login(User user);

	/**
	 * 上传用户头像
	 * 
	 * @param content
	 * @param filename
	 * @return
	 */
	Result uploadProfile(Integer uid, String filename, byte[] content);

	/**
	 * 重新绑定用户和车辆
	 * 
	 * @param user
	 * @param myCar
	 * @return
	 */
	Result rebound(User user, MyCar myCar);

}
