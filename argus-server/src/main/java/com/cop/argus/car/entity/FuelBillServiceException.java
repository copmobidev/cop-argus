package com.cop.argus.car.entity;

import com.cop.argus.account.entity.AccountServiceException;
import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.Result;
import com.cop.argus.common.entity.Result.ResultStatus;
import com.cop.argus.service.common.BasicServiceException;

/**
 * 
 * @author chris.liu
 * 
 */
public class FuelBillServiceException extends BasicServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 481715016227467814L;

	public FuelBillServiceException(int code, String message) {
		super(code, message);
	}

	public FuelBillServiceException(int code) {
		super(code);
	}

	/**
	 * 加油记录service异常处理
	 * 
	 * @param e
	 * @return
	 */
	public static Result handleException(AccountServiceException e) {
		Result result = null;
		switch (e.getCode()) {
		case AccountServiceException.EMAIL_EXISTED:
			result = new Result(ResultStatus.RS_FAIL, null, new Message("抱歉",
					"该邮箱已被其他人使用"));
			break;
		case AccountServiceException.USER_NOT_FOUND:
			result = new Result(ResultStatus.RS_FAIL, null, new Message("抱歉",
					"咩有找到该用户!"));
			break;
		case AccountServiceException.INFO_UPATE_ERROR:
			result = new Result(ResultStatus.RS_FAIL, null, new Message("抱歉",
					"更新用户信息失败!"));
			break;
		default:
			result = new Result(ResultStatus.RS_FAIL, null,
					Message.MSG_SERVER_INNER_ERROR);
			break;
		}
		return result;
	}
}
