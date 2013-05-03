package com.cop.mobi.account.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
	public Result register(User user, MyCarPo myCarPo) {
		Result result = null;
		try {
			if (StringUtils.isNotBlank(user.getName())) {
				boolean userExisted = accountDao.getUserByName(user.getName()) != null;
				if (userExisted) {
					return new Result(ResultStatus.RS_FAIL, new Message("账号",
							"注册失败,该用户名已被使用"));
				}
			}
			if (StringUtils.isNotBlank(user.getName())) {
				boolean userExisted = accountDao
						.getUserByEmail(user.getEmail()) != null;
				if (userExisted) {
					return new Result(ResultStatus.RS_FAIL, new Message("账号",
							"注册失败,该邮箱已被使用"));
				}
			}
			if (StringUtils.isNotBlank(myCarPo.getVin())) {
				boolean carExisted = myCarService.getMyCarByVIN(myCarPo
						.getVin()) != null;
				if (carExisted) {
					return new Result(ResultStatus.RS_FAIL, new Message("账号",
							"注册失败,该OBD设备已被使用"));
				}
			}

			if (accountDao.addUser(user) == 1) {
				UserPo newUserPo = accountDao.getUserByName(user.getName());
				if (newUserPo != null) {
					User addedUser = new User(newUserPo);
					myCarPo.setUid(newUserPo.getId());
					if (myCarService.addMyCar(myCarPo).getStatus() == ResultStatus.RS_OK) {
						return new Result(ResultStatus.RS_OK, addedUser);
					} else {
						accountDao.deleteUser(addedUser.getId());
					}
				}
			}
			return new Result(ResultStatus.RS_FAIL, new Message("账号",
					"注册失败,未知错误"));
		} catch (Exception e) {
			log.error(String.format("%s:register() error with param:%s & %s",
					Tag, user, myCarPo), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result login(User user) {
		try {
			AccountLog.info(user.toString() + " try to login");
			Object data = null;
			UserPo userPo = null;
			if (user.getPwd() != null) {
				if (user.getName() != null) {
					userPo = accountDao.getUserByName(user.getName());
				} else if (user.getEmail() != null) {
					userPo = accountDao.getUserByEmail(user.getEmail());
				}

				if (userPo == null) {
					data = new Message("账号", "登陆失败,用户不存在");
				} else {
					if (user.getPwd().equals(userPo.getPwd())) {
						data = new User(userPo);
						return new Result(ResultStatus.RS_OK, data);
					} else {
						data = new Message("账号", "登陆失败,用户名或密码错误");
					}
				}
			} else {
				data = new Message("账号", "登陆失败,密码错误");
			}
			Result result = new Result(ResultStatus.RS_FAIL, data);
			return result;
		} catch (Exception e) {
			log.error(
					String.format("%s:login() error: with param:%s", Tag, user),
					e);
			return new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
	}

	@Override
	public Result uploadProfile(Integer uid, String filename, byte[] content) {
		Result result = null;
		try {
			String fullPath = String.format("%s/%s", PROFILE_UPLOADED_PATH,
					filename);
			writeFile(fullPath, content);
			int res = (Integer) accountDao.updateUserProfile(uid, filename);
			if (res == 1) {
				UserPo userPo = accountDao.getUserById(uid);
				User user = new User(userPo);
				result = new Result(ResultStatus.RS_OK, user);
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("账号",
						"上传头像失败"));
			}
		} catch (Exception e) {
			log.error(String.format("%s:uploadProfile() error", Tag), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result rebound(User user, MyCar myCar) {

		return null;
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
