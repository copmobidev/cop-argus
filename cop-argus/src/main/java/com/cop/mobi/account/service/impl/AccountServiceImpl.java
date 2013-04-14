package com.cop.mobi.account.service.impl;

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
			error(Tag, "init error", e);
		}
	}

	@Override
	public Result register(User user, MyCar myCar) {
		try {
			Object data = null;
			UserPo userPo = null;
			if (StringUtils.isNotBlank(user.getPwd())) {
				if (user.getName() != null) {
					userPo = accountDao.getUserByName(user.getName());
					if (userPo != null) {
						data = new Message("注册失败", "该用户名已被使用");
						return new Result(ResultStatus.RS_FAIL, data);
					}
				} else if (user.getEmail() != null) {
					userPo = accountDao.getUserByEmail(user.getEmail());
					if (userPo != null) {
						data = new Message("注册失败", "该邮箱已被使用");
						return new Result(ResultStatus.RS_FAIL, data);
					}
				}

				if (userPo == null) {
					accountDao.addUser(user);
					userPo = accountDao.getUserByName(user.getName());
					if (userPo != null) {
						data = new User(userPo);
						myCar.setUid(userPo.getId());
						myCarService.addMyCar(myCar);
						if (myCarService.getMyCarByOBD(userPo.getObd()) != null) {
							return new Result(ResultStatus.RS_OK, data);
						}
					}
				}
			} else {
				data = new Message("注册失败", "密码不能为空");
			}
			Result result = new Result(ResultStatus.RS_FAIL, data);
			return result;
		} catch (Exception e) {
			return new Result(ResultStatus.RS_ERROR, new Message("系统错误",
					"服务器内部错误"));
		}
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
					data = new Message("登陆失败", "用户不存在");
				} else {
					if (user.getPwd().equals(userPo.getPwd())) {
						data = new User(userPo);
						return new Result(ResultStatus.RS_OK, data);
					} else {
						data = new Message("登陆失败", "用户名或密码错误");
					}
				}
			} else {
				data = new Message("登陆失败", "密码错误");
			}
			Result result = new Result(ResultStatus.RS_FAIL, data);
			return result;
		} catch (Exception e) {
			return new Result(ResultStatus.RS_ERROR, new Message("系统错误",
					"服务器内部错误"));
		}
	}

	@Override
	public Result rebound(User user, MyCar myCar) {

		return null;
	}

}
