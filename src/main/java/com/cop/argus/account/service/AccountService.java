package com.cop.argus.account.service;

import org.springframework.web.multipart.MultipartFile;

import com.cop.argus.account.entity.AccountServiceException;
import com.cop.argus.account.entity.User;

/**
 * 
 * @author chris.liu
 * 
 */
public interface AccountService {

	/**
	 * 根据OBD序列号绑定车辆用户信息，返回用户对象
	 * 
	 * @param obd
	 * @return
	 */
	User bound(String obd) throws AccountServiceException;

	/**
	 * 重新绑定用户
	 * 
	 * @param obd
	 * @param vin
	 * @param calid
	 * @param cid
	 * @return
	 * @throws AccountServiceException
	 */
	User rebound(String obd, String vin, String calid, String cid)
			throws AccountServiceException;

	/**
	 * 用户资料更新
	 * 
	 * @param id
	 * @param email
	 * @param name
	 * @param pwd
	 * @param sex
	 * @param birth
	 * @return
	 * @throws AccountServiceException
	 */
	User update(int id, String vin, String calid, String cid, String email,
			String name, String pwd, Integer sex, Long birth)
			throws AccountServiceException;

	/**
	 * 上传用户头像
	 * 
	 * @param uid
	 * @param data
	 * @return
	 * @throws AccountServiceException
	 */
	User uploadProfile(int uid, MultipartFile data)
			throws AccountServiceException;
}
