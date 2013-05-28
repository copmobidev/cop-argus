package com.cop.mobi.account.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cop.mobi.account.entity.User;
import com.cop.mobi.account.entity.UserPo;
import com.cop.mobi.account.service.AccountService;
import com.cop.mobi.account.service.dao.AccountDao;
import com.cop.mobi.common.AbstractService;
import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.mycar.entity.CarBrand;
import com.cop.mobi.mycar.entity.MyCar;
import com.cop.mobi.mycar.entity.MyCarPo;
import com.cop.mobi.mycar.service.MyCarService;
import com.cop.mobi.rest.core.SpringApplicationContext;
import com.cop.mobi.rest.core.TokenUtil;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("accountService")
public class AccountServiceImpl extends AbstractService implements
		AccountService {
	private static final String Tag = "AccountServiceImpl";

	private static final String PROFILE_UPLOADED_PATH = "/data/resources/profile/";

	private static final Logger AccountLog = Logger.getLogger("account-log");

	private static AccountDao accountDao;
	private static MyCarService myCarService;

	static {
		init();
	}

	private static void init() {
		try {
			accountDao = (AccountDao) SpringApplicationContext
					.getBean("accountDao");

			myCarService = (MyCarService) SpringApplicationContext
					.getBean("myCarService");
		} catch (Exception e) {
			log.error(String.format("%s:%s", Tag, "init error"), e);
		}
	}

	@Override
	public Result register(String obd, String sid, CarBrand carBrand,
			long registerTime) {
		Result result = null;
		AccountLog.info(String.format("register:%s,%s@%s", obd, sid,
				carBrand.toLCString(), new Date()));
		User user = null;
		MyCar newCar = null;
		try {
			boolean isUserExisted = accountDao.getUserByOBD(obd) == null ? false
					: true;

			boolean isCarExisted = myCarService.getMyCarBySid(sid) == null ? false
					: true;
			if (isUserExisted || isCarExisted) {
				return new Result(ResultStatus.RS_FAIL, new Message("注册失败",
						"该设备或车辆已注册"));
			}
			accountDao.addUser(obd, registerTime);
			UserPo newUserPo = accountDao.getUserByOBD(obd);
			if (newUserPo != null) {
				myCarService.addMyCar(newUserPo.getId(), sid, carBrand);
				newCar = myCarService.getMyCarBySid(sid);
				if (newCar != null) {
					user = new User(newUserPo.getId(), newUserPo.getObd(),
							newUserPo.getEmail(), newUserPo.getName(),
							newUserPo.getRegisterTime());
					String token = TokenUtil.generateToken(user.getId(),
							newCar.getId(), 1);
					String data = String
							.format("{\"token\":\"%s\",\"profile\":\"%s\",\"carinfo\":\"%s\"}",
									token, user.toLCString(),
									newCar.toLCString());
					result = new Result(ResultStatus.RS_OK, data);
				} else {
					accountDao.deleteUser(newUserPo.getId());
					result = new Result(ResultStatus.RS_ERROR,
							SERVER_INNER_ERROR_MSG);
				}
			} else {
				result = new Result(ResultStatus.RS_ERROR,
						SERVER_INNER_ERROR_MSG);
			}
		} catch (Exception e) {
			log.error(String.format("%s:register() error with param:%s & %s",
					Tag, obd, sid), e);
			if (user != null) {
				accountDao.deleteUser(user.getId());
			}
			if (newCar != null) {
				myCarService.deleteMyCar(newCar.getId());
			}
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result login(UserPo userPo) {
		return null;
	}

	@Override
	public Result update(UserPo userPo, MyCarPo myCarPo) {
		Result result = null;
		try {
			User user = null;
			MyCar myCar = null;
			// update user info
			if (userPo != null) {
				List<String> values = new ArrayList<String>();
				if (StringUtils.isNotBlank(userPo.getEmail())) {
					values.add(String.format("email=%s", userPo.getEmail()));
				}
				if (StringUtils.isNotBlank(userPo.getName())) {
					values.add(String.format("name=%s", userPo.getName()));
				}
				if (StringUtils.isNotBlank(userPo.getPwd())) {
					values.add(String.format("pwd=%s", userPo.getPwd()));
				}
				accountDao.updateUserInfo(StringUtils.join(values, ","));
				UserPo updatedUserPo = accountDao.getUserById(userPo.getId());
				user = new User(updatedUserPo.getId(), updatedUserPo.getObd(),
						updatedUserPo.getEmail(), updatedUserPo.getName(),
						updatedUserPo.getRegisterTime());
			}
			// update mycar info
			if (myCarPo != null) {
				myCar = myCarService.updateMyCarInfo(myCarPo);
			}
			if (user != null && myCar != null) {
				String token = TokenUtil.generateToken(user.getId(),
						myCar.getId(), 1);
				String data = String
						.format("{\"token\":\"%s\",\"profile\":\"%s\",\"carinfo\":\"%s\"}",
								token, user.toLCString(), myCar.toLCString());
				result = new Result(ResultStatus.RS_OK, data);
			} else {
				result = new Result(ResultStatus.RS_FAIL,
						SERVER_INNER_ERROR_MSG);
			}
		} catch (Exception e) {
			log.error(String.format("%s:update() error with param:%s & %s",
					Tag, userPo.getId(), myCarPo.getId()), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result uploadProfile(Integer uid, String filename, byte[] content) {
		Result result = null;
		try {
			// 文件写入磁盘
			String fullPath = String.format("%s/%s", PROFILE_UPLOADED_PATH,
					filename);
			writeFile(fullPath, content);

		} catch (Exception e) {
			log.error(String.format("%s:uploadProfile() error", Tag), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	private void writeFile(String filename, byte[] content) throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fop = new FileOutputStream(file);
		fop.write(content);
		fop.flush();
		fop.close();
	}
}
