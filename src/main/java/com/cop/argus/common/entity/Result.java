package com.cop.argus.common.entity;

import com.google.gson.annotations.Expose;

/**
 * @author chris.liu
 */
public class Result {

    @Expose
    private ResultStatus status;
    @Expose
    private String token;
    @Expose
    private Object data;

    public Result(ResultStatus status, String token, Object data) {
        this.status = status;
        this.token = token;
        this.data = data;
    }

    public enum ResultStatus {
        RS_OK(200), RS_FAIL(400), RS_EXPIRED(401), RS_ERROR(500);

        private int statusCode;

        ResultStatus(int statusCode) {
            this.statusCode = statusCode;
        }

        public int statusCode() {
            return statusCode;
        }
    }
}
