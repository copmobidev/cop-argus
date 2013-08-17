package com.cop.argus.account.service.impl;

import com.cop.argus.account.entity.AccountServiceException;
import com.cop.argus.account.entity.User;
import com.cop.argus.account.service.AccountService;
import com.cop.argus.account.service.dao.AccountDao;
import com.cop.argus.account.service.entity.UserPo;
import com.cop.argus.car.entity.CarBrand;
import com.cop.argus.car.service.CarBrandService;
import com.cop.argus.service.common.BasicService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("accountService")
public class AccountServiceImpl extends BasicService implements AccountService {

	private static final String PROFILE_PREX = "/data/resource/profile/";

	@Autowired
	private CarBrandService carBrandService;

	@Autowired
	private AccountDao accountDao;

	@Override
	public User bound(String obd) throws AccountServiceException {
		User user = null;
		try {
			UserPo userPo = accountDao.getUserByObd(obd);
			if (userPo != null) {
				if (userPo.getRegisterTime() != 0) {
					throw new AccountServiceException(
							AccountServiceException.USER_BOUNDED,
							"account has been bounded!");
				}

				String values = String.format("registerTime=%s", obd,
						new Date().getTime());
				accountDao.updateUserInfo(userPo.getId(), values);

				userPo = accountDao.getUserById(userPo.getId());
				if (userPo != null) {
					CarBrand carBrand = carBrandService.getCarBrandById(userPo
							.getBid());
					user = new User(userPo.getId(), userPo.getObd(), carBrand,
							userPo.getEmail(), userPo.getName(),
							userPo.getSex(), userPo.getBirth(),
							userPo.getProfile(), userPo.getScore(),
							userPo.getLevel(), userPo.getRegisterTime());
				} else {
					throw new AccountServiceException(
							AccountServiceException.USER_NOT_FOUND,
							"no such user found!");
				}
			} else {
				throw new AccountServiceException(
						AccountServiceException.USER_NOT_FOUND,
						"no such user found!");
			}
		} catch (Exception e) {
			throw new AccountServiceException(
					AccountServiceException.UNKNOW_ERROR);
		}
		return user;
	}

	@Override
	public User rebound(String obd, String vin, String calid, String cid)
			throws AccountServiceException {
		User user = null;
		try {
			if (StringUtils.isBlank(obd) || StringUtils.isBlank(cid)) {
				throw new AccountServiceException(
						AccountServiceException.USER_INFO_ERROR);
			}

			UserPo userPo = accountDao.getUserByObd(obd);
			if (userPo != null) {
				boolean flag = true;
				if (calid != null) {
					flag = flag & calid.equals(userPo.getCalid());
				}
				if (cid != null) {
					flag = flag & cid.equals(userPo.getCid());
				}
				if (vin != null) {
					flag = flag & vin.equals(userPo.getVin());
				}
				if (!flag) {
					throw new AccountServiceException(
							AccountServiceException.USER_INFO_ERROR);
				}

				userPo = accountDao.getUserById(userPo.getId());
				if (userPo != null) {
					CarBrand carBrand = carBrandService.getCarBrandById(userPo
							.getBid());
					user = new User(userPo.getId(), userPo.getObd(), carBrand,
							userPo.getEmail(), userPo.getName(),
							userPo.getSex(), userPo.getBirth(),
							userPo.getProfile(), userPo.getScore(),
							userPo.getLevel(), userPo.getRegisterTime());
				} else {
					throw new AccountServiceException(
							AccountServiceException.USER_NOT_FOUND,
							"no such user found!");
				}
			} else {
				throw new AccountServiceException(
						AccountServiceException.USER_NOT_FOUND,
						"no such user found!");
			}
		} catch (Exception e) {
			throw new AccountServiceException(
					AccountServiceException.UNKNOW_ERROR);
		}
		return user;
	}

	@Override
	public User update(int id, String vin, String calid, String cid,
			String email, String name, String pwd, Integer sex, Long birth)
			throws AccountServiceException {
		User user = null;
		try {
			UserPo userInDB = accountDao.getUserById(id);
			if (userInDB != null) {
				if (StringUtils.isNotBlank(email)) {
					UserPo userByEmail = accountDao.getUserByEmail(email);
					if (userByEmail != null) {
						throw new AccountServiceException(
								AccountServiceException.EMAIL_EXISTED,
								"this email has been used!");
					}
				}

				String values = "";
				values = StringUtils.isNotBlank(vin) ? String.format(
						"%s,vin='%s'", values, vin) : values;
				values = StringUtils.isNotBlank(calid) ? String.format(
						"%s,calid='%s'", values, calid) : values;
				values = StringUtils.isNotBlank(cid) ? String.format(
						"%s,cid='%s'", values, cid) : values;
				values = StringUtils.isNotBlank(email) ? String.format(
						"%s,email='%s'", values, email) : values;
				values = StringUtils.isNotBlank(name) ? String.format(
						"%s,name='%s'", values, name) : values;
				values = StringUtils.isNotBlank(pwd) ? String.format(
						"%s,pwd='%s'", values, pwd) : values;
				values = sex == null ? String
						.format("%s,sex='%s'", values, sex) : values;
				values = birth == null ? String.format("%s,birth='%s'", values,
						birth) : values;
				if (values.startsWith(",") && values.length() > 2) {
					values = values.substring(1);
				}
				accountDao.updateUserInfo(id, values);

				UserPo userPo = accountDao.getUserById(id);
				if (userPo != null) {
					CarBrand carBrand = carBrandService.getCarBrandById(userPo
							.getBid());

					user = new User(userPo.getId(), userPo.getObd(), carBrand,
							userPo.getEmail(), userPo.getName(),
							userPo.getSex(), userPo.getBirth(),
							userPo.getProfile(), userPo.getScore(),
							userPo.getLevel(), userPo.getRegisterTime());
				} else {
					throw new AccountServiceException(
							AccountServiceException.USER_NOT_FOUND,
							"no user found");
				}
			} else {
				throw new AccountServiceException(
						AccountServiceException.USER_NOT_FOUND, "no user found");
			}
		} catch (Exception e) {
			throw new AccountServiceException(
					AccountServiceException.UNKNOW_ERROR);
		}
		return user;
	}

	@Override
	public User uploadProfile(int id, MultipartFile data)
			throws AccountServiceException {
		User user = null;
		try {
			UserPo userInDB = accountDao.getUserById(id);
			if (userInDB != null) {
				try {
					String fileName = "";
					FileCopyUtils.copy(data.getBytes(), new File(PROFILE_PREX
							+ fileName));

					String values = String.format(
							"profile='%s', lstUpdateTime=", fileName,
							new Date().getTime());

					accountDao.updateUserInfo(id, values);
				} catch (Exception e) {
					throw new AccountServiceException(
							AccountServiceException.UNKNOW_ERROR,
							"wirte file to disk error");
				}
				userInDB = accountDao.getUserById(id);
				if (userInDB != null) {
					CarBrand carBrand = carBrandService
							.getCarBrandById(userInDB.getBid());
					user = new User(userInDB.getId(), userInDB.getObd(),
							carBrand, userInDB.getEmail(), userInDB.getName(),
							userInDB.getSex(), userInDB.getBirth(),
							userInDB.getProfile(), userInDB.getScore(),
							userInDB.getLevel(), userInDB.getRegisterTime());
				}
			} else {
				throw new AccountServiceException(
						AccountServiceException.USER_NOT_FOUND, "no user found");
			}
		} catch (Exception e) {
			throw new AccountServiceException(
					AccountServiceException.UNKNOW_ERROR);
		}
		return user;
	}
}
