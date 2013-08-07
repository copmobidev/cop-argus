package com.cop.argus.car.entity;

import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.Result;
import com.cop.argus.common.entity.Result.ResultStatus;
import com.cop.argus.service.common.BasicServiceException;

/**
 * @author chris.liu
 */
public class FuelBillServiceException extends BasicServiceException {
    /**
     *
     */
    private static final long serialVersionUID = 481715016227467814L;

    public static final int BILL_EXISTED = 1; // 账单已存在

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
    public static Result handleException(FuelBillServiceException e) {
        Result result = null;
        switch (e.getCode()) {
            case BILL_EXISTED:
                result = new Result(ResultStatus.RS_FAIL, null, new Message("抱歉",
                        "账单已存在"));
                break;
            default:
                result = new Result(ResultStatus.RS_FAIL, null,
                        Message.MSG_SERVER_INNER_ERROR);
                break;
        }
        return result;
    }
}
