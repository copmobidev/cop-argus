package com.cop.argus.other.entity;

import com.cop.argus.common.entity.Message;
import com.cop.argus.common.entity.Result;
import com.cop.argus.common.entity.Result.ResultStatus;
import com.cop.argus.service.common.BasicServiceException;

/**
 * @author chris.liu
 */
public class OtherServiceException extends BasicServiceException {

    /**
     *
     */
    private static final long serialVersionUID = -2783991709895225337L;
    public static final int DATA_ERROR = 1;

    public OtherServiceException(int code, String message) {
        super(code, message);
    }

    public OtherServiceException(int code) {
        super(code);
    }

    /**
     * 行程数据service异常处理
     *
     * @param e
     * @return
     */
    public static Result handleException(OtherServiceException e) {
        Result result = null;
        switch (e.getCode()) {
            case DATA_ERROR:
                result = new Result(ResultStatus.RS_FAIL, null, new Message("抱歉",
                        "上传数据内容错误"));
                break;
            default:
                result = new Result(ResultStatus.RS_FAIL, null,
                        Message.MSG_SERVER_INNER_ERROR);
                break;
        }
        return result;
    }
}
