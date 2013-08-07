package com.cop.argus.service.common;

/**
 * @author chris.liu
 */
public class BasicServiceException extends Exception {

    // 异常类别
    private int code;

    public static final int UNKNOW_ERROR = 0;

    /**
     *
     */
    private static final long serialVersionUID = 8944212891415223218L;

    public BasicServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BasicServiceException(int code) {
        super();
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
