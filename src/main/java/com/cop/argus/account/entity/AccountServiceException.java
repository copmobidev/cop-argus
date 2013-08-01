package com.cop.argus.account.entity;

import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.Result;
import com.cop.argus.common.entity.Result.ResultStatus;
import com.cop.argus.service.common.BasicServiceException;

/**
 * 
 * @author chris.liu
 * 
 */
public class AccountServiceException extends BasicServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3143742811246891186L;

	public static final int USER_NOT_FOUND = 1; // 无该用户
	public static final int INFO_UPATE_ERROR = 2; // 更新用户信息错误
	public static final int EMAIL_EXISTED = 3; // 邮箱已注册
	public static final int USER_BOUNDED = 4; // 用户已绑定
	public static final int USER_INFO_ERROR = 5; // 用户信息不符

	public AccountServiceException(int code, String message) {
		super(code, message);
	}

	public AccountServiceException(int code) {
		super(code);
	}

	/**
	 * 账户信息service异常处理
	 * 
	 * @param e
	 * @return
	 */
	public static Result handleException(AccountServiceException e) {
		Result result = null;
		switch (e.getCode()) {
		case EMAIL_EXISTED:
			result = new Result(ResultStatus.RS_FAIL, null, new Message("抱歉",
					"该邮箱已被其他人使用"));
			break;
		case USER_NOT_FOUND:
			result = new Result(ResultStatus.RS_FAIL, null, new Message("抱歉",
					"咩有找到该用户!"));
			break;
		case INFO_UPATE_ERROR:
			result = new Result(ResultStatus.RS_FAIL, null, new Message("抱歉",
					"更新用户信息失败!"));
			break;
		case USER_INFO_ERROR:
			result = new Result(ResultStatus.RS_FAIL, null, new Message("抱歉",
					"用户信息不一致!"));
			break;
		case USER_BOUNDED:
			result = new Result(ResultStatus.RS_FAIL, null, new Message("抱歉",
					"该用户已被注册!"));
			break;
		default:
			result = new Result(ResultStatus.RS_ERROR, null,
					Message.MSG_SERVER_INNER_ERROR);
			break;
		}
		return result;
	}
}
