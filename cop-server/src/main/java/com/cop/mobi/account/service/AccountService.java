package com.cop.mobi.account.service;

import com.cop.mobi.account.entity.UserPo;
import com.cop.mobi.common.Result;
import com.cop.mobi.mycar.entity.CarBrand;
import com.cop.mobi.rest.core.Token;

/**
 * 
 * @author chris.liu
 * 
 */
public interface AccountService {

	/**
	 * 根据用户和车辆注册
	 * 
	 * @param obd
	 * @param sid
	 * @param carBrand
	 * @return
	 */
	Result register(String obd, String sid, CarBrand carBrand, long registerTime);

	/**
	 * 登陆
	 * 
	 * @param user
	 * @return
	 */
	Result login(UserPo userPo);

	/**
	 * 更新用户信息
	 * 
	 * @param userPo
	 * @param myCarPo
	 * @param carBrand
	 * @return
	 */
	Result updateUserInfo(Token token, UserPo userPo);

	/**
	 * 上传用户头像
	 * 
	 * @param content
	 * @param filename
	 * @return
	 */
	Result uploadProfile(Integer uid, String filename, byte[] content);

	/**
	 * 根据OBD序列号重新绑定用户生成token
	 * 
	 * @param obd
	 * @return
	 */
	Result rebound(String obd);
}
