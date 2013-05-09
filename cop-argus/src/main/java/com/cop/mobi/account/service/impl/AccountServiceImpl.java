package com.cop.mobi.account.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

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
				MyCar newCar = myCarService.getMyCarBySid(sid);
				User user = new User(newUserPo.getObd(), newUserPo.getEmail(),
						newUserPo.getName(), newUserPo.getRegisterTime());
				String data = String.format(
						"{\"profile\":\"%s\",\"carinfo\":\"%s\"}",
						user.toLCString(), newCar.toLCString());
				result = new Result(ResultStatus.RS_OK, data);
			}

		} catch (Exception e) {
			log.error(String.format("%s:register() error with param:%s & %s",
					Tag, obd, sid), e);
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
		// TODO Auto-generated method stub
		return null;
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
